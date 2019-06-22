package com.ns.eval.websocket.service;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class Mine extends Sprite{

    private int damage =5;
    private int radius=5;
    public Mine(int speed, Location location, double angle) {
        super(speed, location, angle);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
