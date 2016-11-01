package com.jcrademacher.tankgame.player;

/**
 * Created by C7 on 10/29/2016.
 */
public class AIPlayer extends Player {

    public AIPlayer(int startX, int startY, int playerNumber) {
        super(startX, startY, playerNumber);
        super.playerType = "AI";
    }

}
