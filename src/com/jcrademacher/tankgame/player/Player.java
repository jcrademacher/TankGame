package com.jcrademacher.tankgame.player;

import java.awt.*;
import java.util.Random;

/**
 * Created by C7 on 10/29/2016.
 */
public abstract class Player {

    protected int xPos;
    protected int yPos;
    // direction is an angle from 0-359 (degrees)
    // 0 = facing right, 90 = facing up, 180 = facing left, 270 = facing down
    protected int direction;

    protected String playerType;

    protected boolean dead;
    protected boolean canShoot;

    private Random rand = new Random();

    // constructor
    public Player(int startX, int startY) {
        if(startX > 800 || startY > 800)
            throw new IllegalArgumentException();
        else {
            xPos = startX;
            yPos = startY;
        }

        direction = rand.nextInt(360);
        dead = false;
        canShoot = true;
    }

    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        if(xPos > 800)
            throw new IllegalArgumentException();
        else
            this.xPos = xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int yPos) {
        if(yPos > 800)
            throw new IllegalArgumentException();
        else
            this.yPos = yPos;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if(direction >= 360)
            throw new IllegalArgumentException();
        else
            this.direction = direction;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void shoot(){
        canShoot = false;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void draw(Graphics2D g) {

    }
}
