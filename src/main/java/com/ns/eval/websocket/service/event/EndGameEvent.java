package com.ns.eval.websocket.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by nasser on 6/21/2019.
 */
public class EndGameEvent extends ApplicationEvent {
    private String winnerName;

    public EndGameEvent(Object source, String winnerName) {
        super(source);
        this.winnerName = winnerName;
    }

    public EndGameEvent(Object source) {
        super(source);
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
