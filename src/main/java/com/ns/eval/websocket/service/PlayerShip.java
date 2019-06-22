package com.ns.eval.websocket.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class PlayerShip extends Sprite {

    private static final int TOP_SPEED = 3;
    public static final int BULLET_SPEED = 10;
    private static final int DEFAULT_BULLET_DAMAGE = 5;
    int life = 5;
    private List<Bullet> bullets = new ArrayList<>();
    private int sceneWidth = 0, scenneHeight = 0;
    private int maxActiveBulletsOfPlayer = 3;
    private List<Mine> mineList = Collections.emptyList();
    private String name,title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlayerShip() {
        setMaxSpeed(TOP_SPEED);
    }

    public PlayerShip clone(){
        return new PlayerShip(this);
    }
    public PlayerShip(int speed, Location location, double angle, int life, int sceneWidth, int scenneHeight,
            String name) {
        super(speed, location, angle);
        super.setMaxSpeed(TOP_SPEED);
        this.life = life;
        this.title = title;
        this.sceneWidth = sceneWidth;
        this.scenneHeight = scenneHeight;
        this.name = name.toLowerCase();
        setRadius(10);
    }

    public PlayerShip(PlayerShip other) {
        super(other);
        this.title = other.title;
        this.life = other.life;
        this.bullets = new ArrayList<>(other.bullets.stream().map(x->new Bullet(x)).collect(Collectors.toList()));
        this.sceneWidth = other.sceneWidth;
        this.scenneHeight = other.scenneHeight;
        this.maxActiveBulletsOfPlayer = other.maxActiveBulletsOfPlayer;
        this.mineList = other.mineList;
        this.name = other.name;
    }

    public void isHit(Bullet b) {
        if(life>0) {
            life -= b.getDamage();
            if(life<0)life=0;
        }
    }

    public boolean isAlive() {
        return life > 0;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerShip that = (PlayerShip) o;
        return Objects.equals(name, that.name);
    }

    public void fire() {
        if(bullets.size()<maxActiveBulletsOfPlayer){
            Bullet b = new Bullet(BULLET_SPEED, getLocation().clone(), getAngle(), 1);
            bullets.add(b);
        }else {
            //find any out of screen bullet if any and use it
            bullets.forEach(b->{
                if(!b.isActive() || !b.isInBound(sceneWidth,scenneHeight)){
                    b.setLocation(getLocation().clone());
                    b.setAngle(getAngle());
                    b.setDamage(DEFAULT_BULLET_DAMAGE);
                    b.setActive(true);
                    b.setSpeed(BULLET_SPEED);
                }
            });
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(int sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    public int getScenneHeight() {
        return scenneHeight;
    }

    public void setScenneHeight(int scenneHeight) {
        this.scenneHeight = scenneHeight;
    }

}
