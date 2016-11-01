package com.jcrademacher.tankgame.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
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

    protected BufferedImage sprite;

    private AffineTransform transformer;

    // constructor
    public Player(int startX, int startY, int playerNumber) {
        if(startX > 800 || startY > 800 || !(playerNumber == 1 || playerNumber == 2))
            throw new IllegalArgumentException();
        else {
            xPos = startX;
            yPos = startY;

            try {
                if (playerNumber == 1)
                    sprite = ImageIO.read(new File("assets/redtank.png"));
                else
                    sprite = ImageIO.read(new File("assets/greentank.png"));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        transformer = new AffineTransform();

        direction = 90;
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

    // moves tank forward
    public void moveForward() {
        int dx = Math.round((float)(Math.cos(Math.toRadians(direction)) * 5.0));
        int dy = Math.round((float)(Math.sin(Math.toRadians(direction)) * 5.0));

        xPos += dx;
        yPos -= dy;
    }

    // moves tank backward
    public void moveBackward() {
        int dx = Math.round((float)(Math.cos(Math.toRadians(direction)) * 2.0));
        int dy = Math.round((float)(Math.sin(Math.toRadians(direction)) * 2.0));

        xPos -= dx;
        yPos += dy;
    }

    public void rotateRight() {
        direction -= 4;
        if(direction < 0)
            direction = 359;
    }

    public void rotateLeft() {
        direction += 4;
        direction %= 360;
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

    public void draw(Graphics2D g2d) {
        // transformer is what rotates the image, first argument is absolute direction in rads, second two are anchor points
        transformer = AffineTransform.getRotateInstance(-Math.toRadians(direction - 90), sprite.getWidth() / 2, sprite.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(transformer, AffineTransformOp.TYPE_BILINEAR);
        // op.filter applies transformation
        g2d.drawImage(op.filter(sprite, null), xPos, yPos, null);
    }
}
