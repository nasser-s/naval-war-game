package com.ns.eval.websocket.ui;

import com.ns.eval.websocket.service.Location;
import com.ns.eval.websocket.service.Scene;
import com.ns.eval.websocket.service.SceneService;
import com.ns.eval.websocket.service.event.EndGameEvent;
import com.ns.eval.websocket.service.event.SceneChangedEvent;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Sadeghi on 6/18/19.
 *
 * @author Sadeghi
 */
@Service
public class GameStateNotifier implements ApplicationListener<EndGameEvent> {
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(EndGameEvent ev) {
        template.convertAndSend("/topic/end-game", ev.getWinnerName());
    }
}
