package com.ns.eval.websocket.ui;

import com.ns.eval.websocket.service.Location;
import com.ns.eval.websocket.service.Scene;
import com.ns.eval.websocket.service.SceneService;
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
 *
 * Recieves SceneChangedEvent events and sends appropriate notification to UI
 */
@Service
public class SceneStateNotifier implements ApplicationListener<SceneChangedEvent> {
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(SceneChangedEvent sceneChangedEvent) {
        template.convertAndSend("/topic/refresh-state", sceneChangedEvent.getNewScene());
    }
}
