package com.ns.eval.websocket.ui.event.outbound;

/**
 * Created by Sadeghi on 6/18/19.
 *
 * @author Sadeghi
 */
public class AuthResponse {
    private String tocken;
    private long expiredate = System.currentTimeMillis()+10000;

    public AuthResponse() {
    }

    public AuthResponse(String tocken) {
        this.tocken = tocken;

    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public long getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(long expiredate) {
        this.expiredate = expiredate;
    }
}
