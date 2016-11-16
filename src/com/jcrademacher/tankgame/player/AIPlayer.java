package com.jcrademacher.tankgame.player;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by C7 on 10/29/2016.
 */
public class AIPlayer extends Player {

    public AIPlayer(int startX, int startY, int playerNumber) {
        super(startX, startY, playerNumber);
        super.playerType = "AI";
    }

    @Override
    public void fireAction(Graphics2D g2d) {
        super.fireAction(g2d);

        Player enemy = super.enemies.get(0);

        int enemyX = enemy.getX();
        int enemyY = enemy.getY();

        // these are flipped to ensure Q1 on unit circle remains positiveX and positiveY
        // helps with trig later on
        int xDistance = enemyX - this.xPos;
        int yDistance = this.yPos - enemyY;

        // calculates distance between tanks
        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

        int theta = (int)Math.toDegrees(Math.acos(xDistance / distance));

        // theta will be angle created by line drawn between two tanks
        if(yDistance < 0)
            theta = 360 - theta;

        int targetTheta = (theta + 180) % 360;

        // rotates tank away from enemy
        if(direction < targetTheta && !isNearWall())
            super.rotateLeft();
        else if(direction > targetTheta && !isNearWall())
            super.rotateRight();

        if(distance < 100) {
            super.accelerate(Player.FORWARD_ACCELERATION);
        }
        else
            super.decelerate();
    }

    private boolean isNearWall() {
        return xPos < 100 || yPos < 100 || xPos > 700 || yPos > 700;
    }
}
