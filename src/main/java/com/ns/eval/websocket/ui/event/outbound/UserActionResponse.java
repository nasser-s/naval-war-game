package com.ns.eval.websocket.ui.event.outbound;

public class UserActionResponse {

    private String content;
    private String type;

    public UserActionResponse() {
    }

    public UserActionResponse(String content, String type) {
        this.content = content;
        this.type = type;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
