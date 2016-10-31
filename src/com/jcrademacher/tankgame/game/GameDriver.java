package com.jcrademacher.tankgame.game;

import com.jcrademacher.tankgame.main.SwingHandler;

import javax.swing.*;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class GameDriver {
    private Game game;

    private Timer timer;

    public GameDriver() {
        game = new Game((String)SwingHandler.gameType.getSelectedItem(), this);
        timer = new Timer(20, game);
    }

    public void start() {
        timer.start();
        System.out.println("Start");
    }

    public void pause() {
        timer.stop();
        System.out.println("Pause");
    }

    public Game getGame() {
        return game;
    }

    // kills all processes involved with gamedriver
    public void killAll() {
        timer.stop();
        game.getFrame().dispose();
    }
}
