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

    private double forceMultiplier = 0;

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
        int p2X = rand.nextInt(400) + 368;
        int p2Y = rand.nextInt(400) + 368;

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

        p1.addEnemy(p2);
        p2.addEnemy(p1);

        frame.add(this);
        frame.setResizable(false);
        frame.setSize(1000,800);
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

        g2d.setColor(new Color(160, 160, 160));
        g2d.fillRect(0,0,800,800);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(800,0,5,800);

        g2d.setColor(new Color(71, 113, 23));
        g2d.fillRect(805,0,200,800);

        drawHealthPanel(g2d);

        if(upPressing)
            p1.accelerate(Player.FORWARD_ACCELERATION);
        else if(downPressing)
            p1.accelerate(Player.BACKWARD_ACCELERATION);
        else
            p1.decelerate();

        if(leftPressing)
            p1.rotateLeft();
        if(rightPressing)
            p1.rotateRight();

        p1.draw(g2d);
        p2.draw(g2d);
        p1.drawBullets(g2d);
        p2.drawBullets(g2d);
    }

    private void drawHealthPanel(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Player 1 Health:", 825, 30);
        g2d.drawString("Player 2 Health:", 825, 400);

        g2d.setColor(Color.PINK);
        g2d.drawRect(825, 50, 80, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
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
