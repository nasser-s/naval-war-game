package com.ns.eval.websocket.service;

import com.ns.eval.websocket.Constants;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 *
 * Super class of all game sprites, manages sprite location and phisycal properties live velocity and angle
 */
public class Sprite {

    private static final double MAX_TURN_RATE = 1;
    private int speed=0,maxSpeed=10,maxSpeedChangeRate=1;
    private boolean visible = true;
    private Location location = new Location(0,0);
    private double angle = 0;
    private int radius = 10;
    int sceneWidth = Constants.SCENE_WIDTH;
    int sceneHeight=Constants.SCENE_HEIGHT;

    public Sprite() {
    }

    public Sprite(Sprite other) {
        this.speed = other.speed;
        this.maxSpeed = other.maxSpeed;
        this.maxSpeedChangeRate = other.maxSpeedChangeRate;
        this.visible = other.visible;
        this.location = other.location!=null?other.location.clone():null;
        this.angle = other.angle;
        this.radius = other.radius;
        this.sceneWidth = other.sceneWidth;
        this.sceneHeight = other.sceneHeight;
    }


    public Sprite(int speed, Location location, double angle) {
        this.speed = speed;
        this.location = location.clone();
        this.angle = angle;
    }

    private Location nextLocation(int timeDiff){
        if(speed==0){
            return this.location;
        }
        double speedx = speed*Math.cos(angle)*timeDiff;
        double speedy = speed*Math.sin(angle)*timeDiff;
        Double nextX = location.getX()+ location.getX()*speedx;
        Double nextY = location.getY()+ location.getY()*speedy;
        return new Location(nextX.intValue(),nextY.intValue());
    }

    public void gotoNextLocation(int timeDiff){
        //consider game boundry
        double speedx = speed*Math.cos(angle-Math.PI/2)*timeDiff;
        double speedy = speed*Math.sin(angle-Math.PI/2)*timeDiff;
        Double nextX = location.getX()+ speedx;
        Double nextY = location.getY()+ speedy;
        if(nextX.intValue() == location.getX() && nextY.intValue()==location.getY()){
            return;
        }else {
            int x = Math.min(sceneWidth,nextX.intValue());
            int y = Math.min(sceneHeight,nextY.intValue());
            if(x<0)x=0;
            if(y<0)y=0;
            this.location = new Location(x, y);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isVisible() {
        return visible;
    }
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovingObject{");
        sb.append("speed=").append(speed);
        sb.append(", location=").append(location);
        sb.append(", angle=").append(angle);
        sb.append('}');
        return sb.toString();
    }

    public boolean colidesWith(Sprite other){
        int y1 = this.location.getY();
        int x1 = this.location.getX();
        int y2 = other.location.getY();
        int x2 = other.location.getX();
        Double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return  (distance.intValue()<(this.radius+other.radius));
    }

    public void speedUp(int value){
        if(speed<maxSpeed)speed+=Math.min(value,maxSpeedChangeRate);
    }
    public void speedDown(int value){
        if(speed>=value)speed-=Math.min(value,maxSpeedChangeRate);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeedChangeRate() {
        return maxSpeedChangeRate;
    }

    public void setMaxSpeedChangeRate(int maxSpeedChangeRate) {
        this.maxSpeedChangeRate = maxSpeedChangeRate;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void turnRight(double value){
        angle+=Math.min(MAX_TURN_RATE,value);
    }
    public void turnLeft(double value){
        angle-=Math.min(MAX_TURN_RATE,value);
    }

    public void increaseSpeed(){
        if(getSpeed()<getMaxSpeed()){
            speed+=getMaxSpeedChangeRate();
        }
    }
    public void decreaseSpeed(){
        if(getSpeed()>-getMaxSpeed()){
            speed-=getMaxSpeedChangeRate();
        }
    }
    public boolean isInBound(int sceneWidth, int sceneHeight) {
        if (getLocation().getY() < 0 || getLocation().getY() > sceneHeight) {
            return false;
        }
        if (getLocation().getX() < 0 || getLocation().getX() > sceneWidth) {
            return false;
        }
        return true;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(int sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(int sceneHeight) {
        this.sceneHeight = sceneHeight;
    }
}
