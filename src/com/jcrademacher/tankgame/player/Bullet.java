package com.jcrademacher.tankgame.player;

import java.awt.*;

/**
 * Created by C7 on 10/29/2016.
 */
public class Bullet {

    private int xPos;
    private int yPos;

    private int direction;

    private boolean isActive;

    private boolean hasHit;

    public Bullet(){
        this(0);
    }

    public Bullet(int direction){
        this(direction, false, 0, 0);
    }

    public Bullet(int direction, boolean isActive, int xPos, int yPos){
        this.direction = direction;
        this.isActive = isActive;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw(Graphics2D g2d) {
        if(isActive) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(xPos, yPos, 4, 4);

            // controls speed/direction of bullet
            int dx = Math.round((float) (Math.cos(Math.toRadians(direction)) * 8.0));
            int dy = Math.round((float) (Math.sin(Math.toRadians(direction)) * 8.0));

            xPos += dx;
            yPos -= dy;

            if(xPos < 0 || yPos < 0 || xPos > 800 || yPos > 800)
                isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public int getY() {
        return yPos;
    }

    public int getX() {
        return xPos;
    }
}
