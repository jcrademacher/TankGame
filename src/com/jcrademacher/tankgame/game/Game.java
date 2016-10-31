package com.jcrademacher.tankgame.game;

import com.jcrademacher.tankgame.player.AIPlayer;
import com.jcrademacher.tankgame.player.GeneticPlayer;
import com.jcrademacher.tankgame.player.HumanPlayer;
import com.jcrademacher.tankgame.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Game extends JPanel implements ActionListener, WindowListener {
    private Player p1;
    private Player p2;

    private JFrame frame = new JFrame("Tank Game");

    private GameDriver driver;

    // creates players as needed with specified game type
    public Game(String gameType, GameDriver driver) {
        this.driver = driver;
        frame.addWindowListener(this);

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

        frame.setResizable(false);
        frame.setSize(800,800);
        frame.setVisible(true);
    }

    public boolean isFrameVisible() {
        return frame.isVisible();
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ACTION");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // stops timer execution when window is closed
        driver.killAll();
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
