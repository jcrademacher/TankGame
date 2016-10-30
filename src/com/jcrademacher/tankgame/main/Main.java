package com.jcrademacher.tankgame.main;

import com.jcrademacher.tankgame.game.GameDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Main implements ActionListener {
    JFrame mainframe = new JFrame("Tank Game");
    JPanel content;

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        // creates content panel
        content = SwingHandler.createController(this);

        mainframe.setContentPane(content);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(true);
        mainframe.setSize(350,200);
        mainframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String text = ((JButton)src).getText();

        // starts game with GameDriver, fires actionPerformed using javax.swing.Timer to Game class
        if(text.equals("Start")) {
            // gets gameType from combobox
            GameDriver driver = new GameDriver();
            driver.start();
        }
    }
}
