package com.ns.eval.websocket.service;

import com.ns.eval.websocket.Constants;
import org.apache.commons.collections.map.ListOrderedMap;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Sadeghi on 6/19/19.
 *
 * @author Sadeghi
 */
public class Scene implements Serializable {

    public static final int DEFAULT_LIFE = 10;
    private int width = Constants.SCENE_WIDTH, height = Constants.SCENE_HEIGHT;

    private transient boolean active = true;
    private Map<String, PlayerShip> players = new ListOrderedMap();// consider using ListOrderedMap to preserver player order

    public Scene clone(){
        return new Scene(this);
    }
    public Scene(Scene other) {
        this.width = other.width;
        this.height = other.height;
        this.active = other.active;

        Set<Map.Entry<String, PlayerShip>> entries = other.players.entrySet();
        for (Iterator<Map.Entry<String, PlayerShip>> iterator = entries.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, PlayerShip> entry = iterator.next();
            this.players.put(entry.getKey(),entry.getValue());
        }

    }

    public Scene() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public synchronized void update(int timeDiff) {
        if (!active) {
            return;
        }
        players.values().forEach(ship -> {
            ship.gotoNextLocation(timeDiff);
            ship.getBullets().forEach(b -> {
                if (b.isActive()) {
                    b.gotoNextLocation(timeDiff);
                }
            });
        });
        checkCollisions();
    }


    public Optional<PlayerShip> getPlayerByName(String name) {
        if (!players.containsKey(name.toLowerCase())) {
            return Optional.empty();
        }
        return Optional.of(players.get(name.toLowerCase()));
    }

    public void addPlayer(String name,String title) {
        if (players.isEmpty()) {
            PlayerShip ship = new PlayerShip(0, new Location(100, 100), 0d, DEFAULT_LIFE, width, height,
                    name.toLowerCase());
            ship.setTitle(title);
            players.put(name.toLowerCase(),
                    ship);
        } else if (players.size() == 1) {
            PlayerShip ship = new PlayerShip(0, new Location(width - 100, height - 100), 0d, DEFAULT_LIFE,
                    width, height,
                    name.toLowerCase());
            ship.setTitle(title);
            players.put(name.toLowerCase(),
                    ship);

        } else {
            //exceeds
        }

    }

    /**
     *
     */
    private void checkCollisions() {
        if (!active) {
            return;
        }
        Collection<PlayerShip> playerSet1 = players.values();
        Collection<PlayerShip> playerSet2 = players.values();
        for (Iterator<PlayerShip> iterator = playerSet2.iterator(); iterator.hasNext(); ) {
            PlayerShip p2 = iterator.next();
            for (Iterator<PlayerShip> playerShipIterator = playerSet1.iterator(); playerShipIterator.hasNext(); ) {
                PlayerShip p1 = playerShipIterator.next();
                if (p1.getName().equals(p2.getName())) {
                    continue;
                }
                if (p1.colidesWith(p2)) {
                    p1.life = 0;
                    p2.life = 0;
                    this.setActive(false);
                }
                p1.getBullets().forEach(b1 -> {
                    if (b1.isActive() && b1.colidesWith(p2)) {
                        p2.isHit(b1);
                        if (!p2.isAlive()) {
                            this.setActive(false);
                        }
                        b1.setActive(false);
                        System.out.println("HIT " + p2.getName());
                    }
                });
            }
        }


    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<String, PlayerShip> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, PlayerShip> players) {
        this.players = players;
    }
}
