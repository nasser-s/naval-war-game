package com.ns.eval.websocket.ui;

import com.ns.eval.websocket.service.PlayerShip;
import com.ns.eval.websocket.service.SceneService;
import com.ns.eval.websocket.ui.event.inbound.AuthRequest;
import com.ns.eval.websocket.ui.event.outbound.UserActionResponse;
import com.ns.eval.websocket.ui.event.inbound.UserAction;
import com.ns.eval.websocket.ui.event.outbound.AuthResponse;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MainController {

    @Autowired
    SceneService sceneService;

    @MessageMapping("/login")
    @SendTo("/topic/login")
    public AuthResponse auth(@Payload AuthRequest req,@Header("simpSessionId") String sessionId) throws Exception {
        Thread.sleep(100); // simulated delay
        AuthResponse ticket = new AuthResponse(UUID.randomUUID().toString());
        // add new user to scene if possible
        sceneService.addPlayer(sessionId, StringUtils.isEmpty(req.getPrincipal())?sessionId:req.getPrincipal());
        return ticket;
    }

    @MessageMapping("/restart")
    @SendTo("/topic/restart")
    public String restart(@Header("simpSessionId") String sessionId) throws Exception {
        System.out.println("Restart");
        sceneService.restart();
        return "DONE";
    }

    @MessageMapping("/user-action")
    @SendTo("/topic/user-action-resp")
    public UserActionResponse userEvent(@Payload UserAction message,@Header("simpSessionId") String sessionId) throws Exception {

        long liveCount = sceneService.getScene().getPlayers().values().stream().filter(x -> x.isAlive()).count();
        if(liveCount<2){
            sceneService.restart();
            return new UserActionResponse("END GAME","Type1");

        }
        sceneService.getScene().getPlayerByName(sessionId).ifPresent(player->{
            System.out.println(sessionId+" "+message.getType()+" "+player.isAlive()+" "+player.getLife());
            if(!player.isAlive())return;
            switch (message.getType()){
                case UP: player.increaseSpeed();
                    break;
                case DOWN:player.decreaseSpeed();
                    break;
                case LEFT:player.turnLeft(.1);
                    break;
                case RIGHT:player.turnRight(.1);
                    break;
                case FIRE:player.fire();
                    break;
            }
        });

        return new UserActionResponse(sessionId,"Type1");
    }


}
