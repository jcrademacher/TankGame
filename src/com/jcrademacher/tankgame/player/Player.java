package com.jcrademacher.tankgame.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by C7 on 10/29/2016.
 */
public abstract class Player {

    public static final int FORWARD_ACCELERATION = 1;
    public static final int BACKWARD_ACCELERATION = -1;

    protected int xPos;
    protected int yPos;
    // direction is an angle from 0-359 (degrees)
    // 0 = facing right, 90 = facing up, 180 = facing left, 270 = facing down
    protected int direction;

    protected String playerType;

    protected boolean dead;

    private Random rand = new Random();

    protected BufferedImage sprite;

    private AffineTransform transformer;
    private Bullet[] bullets = new Bullet[30];

    protected ArrayList<Player> enemies = new ArrayList<>();

    private double forceMultiplier = 0;
    private Shape collisionBox;

    private int health;

    // constructor
    public Player(int startX, int startY, int playerNumber) {
        if(startX > 800 || startY > 800 || !(playerNumber == 1 || playerNumber == 2))
            throw new IllegalArgumentException();
        else {
            xPos = startX;
            yPos = startY;

            health = 50;

            // collision box slightly smaller than actual tank
            // these just so happen to be the coordinates that fit the tank snugly
            collisionBox = new Rectangle(xPos+6, yPos+7, 20,22);

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

        // inits bullets
        for(int x = 0; x < bullets.length; x++)
            bullets[x] = new Bullet();

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

    // canMove takes a dx and dy, and tests if the proposed move is valid
    private boolean canMove(int dx, int dy) {
        Rectangle[] collisionBoxes = new Rectangle[enemies.size()];
        Rectangle collisionBoxSelf = new Rectangle(xPos + dx + 2,yPos - dy + 2,28,28);

        // for every rectangle in enemy collisionBoxes...
        for(int x = 0; x < collisionBoxes.length; x++) {
            Player e = enemies.get(x);
            collisionBoxes[x] = new Rectangle(e.getX() + 2, e.getY() + 2, 28, 28);
        }

        // if hit wall, forceMultiplier to 0
        if(xPos + dx > 768 || yPos - dy > 750 || xPos + dx < 0 || yPos - dy < 0) {
            forceMultiplier = 0;
            return false;
        }

        // if tank hits enemy
        for(Rectangle r : collisionBoxes) {
            if(collisionBoxSelf.intersects(r)) {
                forceMultiplier = 0;
                return false;
            }
        }

        return !dead;

    }

    // essentially "pushes" tank along
    public void accelerate(int accelDir) {
        // forward acceleration pushes tank forward
        if(accelDir == this.FORWARD_ACCELERATION) {
            // force multiplier is how much the sin and cos values of direction are multiplied by,
            // and thus determines how far the tank moves per 1 frame
            if (forceMultiplier < 6.0)
                forceMultiplier += 0.2;
            else
                forceMultiplier = 6.0;
        }
        // backward acceleration pushes tank backward (max back speed is limited to -3.0)
        else if(accelDir == this.BACKWARD_ACCELERATION) {
            if (forceMultiplier > -3.0)
                forceMultiplier -= 0.2;
            else
                forceMultiplier = -3.0;
        }

        this.move();
    }

    // decelerate slows down movement no matter what direction tank is going
    // --> always wants to bring forceMultiplier to 0
    public void decelerate() {
        if (forceMultiplier > 0)
            forceMultiplier -= 0.1;
        else if(forceMultiplier < 0)
            forceMultiplier += 0.1;
        else
            forceMultiplier = 0;

        this.move();
    }

    // moves tank forward
    private void move() {
        int dx = Math.round((float)(Math.cos(Math.toRadians(direction)) * forceMultiplier));
        int dy = Math.round((float)(Math.sin(Math.toRadians(direction)) * forceMultiplier));

        // tests if tank can move
        if(canMove(dx, dy)) {
            xPos += dx;
            yPos -= dy;
        }
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

    // adds an enemy for Player to know about
    public void addEnemy(Player player) {
        enemies.add(player);
    }

    // gets enemies
    public Player[] getEnemies() {
        return (Player[])(enemies.toArray());
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

    // shoots bullets, bullets come out of tip of tank shooter, will bounce once before disappearing
    public void shoot(){
        for(int x = 0; x < bullets.length; x++) {
            if(!bullets[x].isActive()) {
                int dx = Math.round((float)(Math.cos(Math.toRadians(direction)) * 20));
                int dy = Math.round((float)(Math.sin(Math.toRadians(direction)) * 20));

                bullets[x] = new Bullet(direction, true, xPos + 14 + dx, yPos + 14 - dy);
                return;
            }
        }
    }

    // draws all bullets associated with tank that shot them
    public void drawBullets(Graphics2D g2d) {
        for(Bullet b : bullets)
            b.draw(g2d);
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public Shape getCollisionBox() {
        return collisionBox;
    }

    // draws tank, with AffineTransform (transformer) rotating the png
    public void draw(Graphics2D g2d) {
        // transformer is what rotates the image, first argument is absolute direction in rads, second two are anchor points
        transformer = AffineTransform.getRotateInstance(-Math.toRadians(direction - 90), sprite.getWidth() / 2, sprite.getHeight() / 2);
        collisionBox = new Rectangle(xPos+6, yPos+7, 20,22);

        AffineTransformOp op = new AffineTransformOp(transformer, AffineTransformOp.TYPE_BILINEAR);
        // op.filter applies transformation
        g2d.drawImage(op.filter(sprite, null), xPos, yPos, null);

        // rotates collision box in correct order
        g2d.setColor(Color.BLACK);
        AffineTransform rotateBox = new AffineTransform();
        rotateBox.rotate(-Math.toRadians(direction - 90), xPos + sprite.getWidth()/2, yPos + sprite.getHeight()/2);
        collisionBox = rotateBox.createTransformedShape(collisionBox);
        g2d.draw(collisionBox);
    }
}
