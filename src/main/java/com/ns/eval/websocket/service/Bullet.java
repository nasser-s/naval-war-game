package com.ns.eval.websocket.service;

import java.io.Serializable;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class Bullet extends Sprite  implements Serializable {

    public static final int BULLET_DIMENSION = 32;
    private int damage = 5;
    private boolean active = true;

    public Bullet(Bullet other) {
        super(other);
        this.damage = other.damage;
        this.active = other.active;
    }

    public Bullet clone(){
        return new Bullet(this);
    }
    public Bullet(int speed, Location location, double angle, int damage) {
        super(speed, location.clone(), angle);
        this.damage = damage;
    }

    public boolean isInBound(int sceneWidth, int sceneHeight) {
        if (getLocation().getY() < BULLET_DIMENSION || getLocation().getY() > sceneHeight- BULLET_DIMENSION) {
            return false;
        }
        if (getLocation().getX() < BULLET_DIMENSION || getLocation().getX() > sceneWidth- BULLET_DIMENSION) {
            return false;
        }
        return true;
    }

    @Override
    public void gotoNextLocation(int timeDiff) {
        super.gotoNextLocation(timeDiff);
        if(!isInBound(sceneWidth,sceneWidth)){
            setActive(false);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isActive() {
        return active;
    }
    public boolean getActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


}
