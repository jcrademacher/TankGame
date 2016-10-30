package com.jcrademacher.tankgame.game;

import com.jcrademacher.tankgame.main.SwingHandler;
import com.jcrademacher.tankgame.player.AIPlayer;
import com.jcrademacher.tankgame.player.GeneticPlayer;
import com.jcrademacher.tankgame.player.HumanPlayer;
import com.jcrademacher.tankgame.player.Player;

import javax.swing.*;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class GameDriver {
    private Game game;

    private Timer timer;

    public GameDriver() {
        game = new Game((String)SwingHandler.gameType.getSelectedItem());
        timer = new Timer(20, game);
    }

    public void start() {
        timer.start();
    }
}
