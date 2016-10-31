package com.jcrademacher.tankgame.player;

/**
 * Created by C7 on 10/29/2016.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(int startX, int startY){
        super(startX, startY);
        super.playerType = "HUMAN";
    }
}
