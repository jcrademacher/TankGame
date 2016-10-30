package com.jcrademacher.tankgame.player;

/**
 * Created by C7 on 10/29/2016.
 */
public abstract class Player {

    protected int xPos;
    protected int yPos;
    protected int direction;

    protected String playerType;

    protected boolean dead;
    protected boolean canShoot;


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
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

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
}
