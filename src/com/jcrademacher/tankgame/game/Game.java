package com.jcrademacher.tankgame.game;

import com.jcrademacher.tankgame.player.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Game extends JPanel implements ActionListener, WindowListener, KeyListener {
    private Player p1;
    private Player p2;

    private JFrame frame = new JFrame("Tank Game");

    private GameDriver driver;

    private boolean upPressing = false;
    private boolean leftPressing = false;
    private boolean rightPressing = false;
    private boolean downPressing = false;

    // creates players as needed with specified game type
    public Game(String gameType, GameDriver driver) {
        this.driver = driver;
        frame.addWindowListener(this);
        this.setFocusable(true);
        this.addKeyListener(this);

        Random rand = new Random();

        // p1 will always be in top left quadrant
        int p1X = rand.nextInt(400);
        int p1Y = rand.nextInt(400);

        // p2 will always be in bottom right quadrant
        int p2X = rand.nextInt(400) + 400;
        int p2Y = rand.nextInt(400) + 400;

        if(gameType.equals("Human v AI")) {
            p1 = new HumanPlayer(p1X, p1Y, 1);
            p2 = new AIPlayer(p2X, p2Y, 2);
        }
        else if(gameType.equals("Human v Genetic")) {
            p1 = new HumanPlayer(p1X, p1Y, 1);
            p2 = new GeneticPlayer(p2X, p2Y, 2);
        }
        else if(gameType.equals("Genetic v Genetic")) {
            p1 = new GeneticPlayer(p1X, p1Y, 1);
            p2 = new GeneticPlayer(p2X, p2Y, 2);
        }
        else if(gameType.equals("Genetic v AI")) {
            p1 = new GeneticPlayer(p1X, p1Y, 1);
            p2 = new AIPlayer(p2X, p2Y,2 );
        }
        else if(gameType.equals("AI v AI")) {
            p1 = new AIPlayer(p1X, p1Y, 1);
            p2 = new AIPlayer(p2X, p2Y, 2);
        }

        frame.add(this);
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

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(new Color(204, 255, 204));
        g2d.fillRect(0,0,800,800);

        if(upPressing)
            p1.moveForward();
        if(downPressing)
            p1.moveBackward();
        if(leftPressing)
            p1.rotateLeft();
        if(rightPressing)
            p1.rotateRight();

        p1.draw(g2d);
        p2.draw(g2d);
        p1.drawBullets(g2d);
        p2.drawBullets(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        System.out.println("ACTION");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // if player is human
        if(p1.getPlayerType().equals("HUMAN")) {
            if(code == KeyEvent.VK_UP)
                upPressing = true;
            if(code == KeyEvent.VK_DOWN)
                downPressing = true;
            if(code == KeyEvent.VK_LEFT)
                leftPressing = true;
            if(code == KeyEvent.VK_RIGHT)
                rightPressing = true;
            if(code == KeyEvent.VK_SPACE)
                p1.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // if player is human
        if(p1.getPlayerType().equals("HUMAN")) {
            if(code == KeyEvent.VK_UP)
                upPressing = false;
            if(code == KeyEvent.VK_DOWN)
                downPressing = false;
            if(code == KeyEvent.VK_LEFT)
                leftPressing = false;
            if(code == KeyEvent.VK_RIGHT)
                rightPressing = false;
        }
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
