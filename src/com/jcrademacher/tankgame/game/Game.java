package com.jcrademacher.tankgame.game;

import com.jcrademacher.tankgame.player.AIPlayer;
import com.jcrademacher.tankgame.player.GeneticPlayer;
import com.jcrademacher.tankgame.player.HumanPlayer;
import com.jcrademacher.tankgame.player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Game extends JPanel implements ActionListener {
    private Player p1;
    private Player p2;

    // creates players as needed with specified game type
    public Game(String gameType) {
        if(gameType.equals("Human v AI")) {
            p1 = new HumanPlayer();
            p2 = new AIPlayer();
        }
        else if(gameType.equals("Human v Genetic")) {
            p1 = new HumanPlayer();
            p2 = new GeneticPlayer();
        }
        else if(gameType.equals("Genetic v Genetic")) {
            p1 = new GeneticPlayer();
            p2 = new GeneticPlayer();
        }
        else if(gameType.equals("Genetic v AI")) {
            p1 = new GeneticPlayer();
            p2 = new AIPlayer();
        }
        else if(gameType.equals("AI v AI")) {
            p1 = new AIPlayer();
            p2 = new AIPlayer();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ACTION");
    }
}
