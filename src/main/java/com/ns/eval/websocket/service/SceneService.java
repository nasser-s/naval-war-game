package com.ns.eval.websocket.service;

import com.ns.eval.websocket.service.event.EndGameEvent;
import com.ns.eval.websocket.service.event.SceneChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sadeghi on 6/20/19.
 *
 * @author Sadeghi
 */
@Service
public class SceneService {

    private Scene scene = new Scene();
    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(fixedDelayString="${scene.refresh-delay-millis}")
    public void updateScene(){
        if(scene.getPlayers().isEmpty())return;
        List<PlayerShip> deadPlayers = scene.getPlayers().values().stream().filter(p -> !p.isAlive()).collect(Collectors.toList());
        if(deadPlayers.isEmpty()) {
            scene.update(1);
            publisher.publishEvent(new SceneChangedEvent(getScene(), System.currentTimeMillis(), this));
        }

        if(!deadPlayers.isEmpty()){
            List<String> livePlayers = scene.getPlayers().values().stream().filter(p -> p.isAlive()).map(x -> x.getTitle()).collect(Collectors.toList());
            publisher.publishEvent(new EndGameEvent(this,String.join(",",livePlayers)));
        }
    }

    public Scene getScene() {
        synchronized(scene) {
            return scene.clone();
        }
    }

    public void addPlayer(String pid,String title){
        if(pid==null) return;
        synchronized(scene) {
            scene.addPlayer(pid,title);
        }
    }

    public void restart(){
        synchronized (scene) {
            scene.getPlayers().clear();
            scene.setActive(true);
        }
    }
}
