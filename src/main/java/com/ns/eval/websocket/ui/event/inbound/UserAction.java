package com.ns.eval.websocket.ui.event.inbound;

public class UserAction {

    public enum Type{
        UP,DOWN,LEFT,RIGHT,FIRE
    }

    private String playerName;
    private String prop;
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public UserAction() {
    }

    public UserAction(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
