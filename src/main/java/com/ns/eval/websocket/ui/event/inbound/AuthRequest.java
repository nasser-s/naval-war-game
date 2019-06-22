package com.ns.eval.websocket.ui.event.inbound;

/**
 * Created by Sadeghi on 6/18/19.
 *
 * @author Sadeghi
 */
public class AuthRequest {
    private String principal,credintial;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredintial() {
        return credintial;
    }

    public void setCredintial(String credintial) {
        this.credintial = credintial;
    }
}
