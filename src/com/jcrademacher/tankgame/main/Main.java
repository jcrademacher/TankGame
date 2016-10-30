package com.jcrademacher.tankgame.main;

import javax.swing.*;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Main extends JPanel {
    JFrame mainframe = new JFrame("Tank Game");

    JButton load = new JButton("");

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        mainframe.setContentPane(this);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setSize(400,400);
        mainframe.setVisible(true);

        initGUI();
    }

    private void initGUI() {

    }
}
