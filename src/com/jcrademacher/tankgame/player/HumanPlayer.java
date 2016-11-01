package com.jcrademacher.tankgame.player;

/**
 * Created by C7 on 10/29/2016.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(int startX, int startY, int playerNumber){
        super(startX, startY, playerNumber);
        super.playerType = "HUMAN";
    }
}
