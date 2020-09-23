/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nzhinusoft.test.models;

import com.nzhinusoft.test.App;
import java.util.ArrayList;

/**
 * Representation d'une tondeuse. Implemente la logique metier du probleme
 * @author Isaac
 */
public class Tondeuse {

    int x;
    int y;
    Direction direction;

    public static ArrayList<Direction> directions = new ArrayList<>(4);

    static {
        directions.add(0, Direction.N);
        directions.add(1, Direction.W);
        directions.add(2, Direction.S);
        directions.add(3, Direction.E);
    }

    public Tondeuse(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Direction getDirection() {
        return direction;
    }

    public void updatePosition(Operations operation) {

        if (null != operation) switch (operation) {
            case D:
                this.direction = directions.get((directions.indexOf(this.direction) - 1+4)%4);
                break;
            case G:
                this.direction = directions.get((directions.indexOf(this.direction) + 1)%4);
                break;
            case A:
                if (direction == Direction.N) {
                    y = (y+1)<= App.getyPelouse()?y+1:y;
                } else if (direction == Direction.W) {
                    x = (x-1)>=0?x-1:x;
                } else if (direction == Direction.S) {
                    y = (y-1)>=0?y-1:y;
                } else if (direction == Direction.E) {
                     x = (x+1)<= App.getxPelouse()?x+1:x;
                }   break;
            default:
                break;
        }
    }
}
