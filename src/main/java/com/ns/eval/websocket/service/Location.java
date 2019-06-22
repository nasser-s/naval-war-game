package com.ns.eval.websocket.service;

import java.util.Objects;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class Location {

    public Location(Location other) {
        this.x = other.x;
        this.y = other.y;
    }
    public Location clone(){
        return new Location(x,y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    int x = 0,y = 0;

    public Location() {
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    public static Location of(int x,int y){
        return new Location(x,y);
    }
}
