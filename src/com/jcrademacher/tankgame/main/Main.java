package com.jcrademacher.tankgame.main;

import javax.swing.*;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Main {
    JFrame mainframe = new JFrame("Tank Game");
    JPanel content;

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        content = SwingHandler.createController();

        mainframe.setContentPane(content);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(true);
        mainframe.setSize(350,200);
        mainframe.setVisible(true);
    }
}
