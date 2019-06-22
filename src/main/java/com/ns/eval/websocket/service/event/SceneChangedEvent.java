package com.ns.eval.websocket.service.event;

import com.ns.eval.websocket.service.Scene;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class SceneChangedEvent extends ApplicationEvent {
    //Scene oldScene;
    private Scene newScene;
    private long time = System.currentTimeMillis();

    public SceneChangedEvent(Scene newScene, long time, Object source) {
        super(source);
        this.newScene = newScene;
        this.time = time;
    }

    public Scene getNewScene() {
        return newScene;
    }

    public void setNewScene(Scene newScene) {
        this.newScene = newScene;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
