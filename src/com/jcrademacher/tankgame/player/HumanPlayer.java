package com.jcrademacher.tankgame.player;

import java.awt.*;

/**
 * Created by C7 on 10/29/2016.
 */
public class HumanPlayer extends Player {

    // class is literally just a placeholder to represent a human player
    public HumanPlayer(int startX, int startY, int playerNumber){
        super(startX, startY, playerNumber);
        super.playerType = "HUMAN";
    }

    // actions already handled through player input in Game class
    public void fireAction(Graphics2D g2d) {
        super.fireAction(g2d);
    }
}
