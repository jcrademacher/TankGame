package com.jcrademacher.tankgame.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class SwingHandler {

    public static JPanel createController(Main main) {
        JPanel panel = new JPanel();
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton loadButton = new JButton("Import Species");
        JButton saveButton = new JButton("Export Species");
        JButton startButton = new JButton("Start");

        loadButton.addActionListener(main);
        saveButton.addActionListener(main);
        startButton.addActionListener(main);

        JLabel type = new JLabel("Select game type:");

        String[] gameTypes = {"Human v AI", "Human v Genetic", "Genetic v Genetic", "Genetic v AI", "AI v AI"};

        JComboBox<String> gameType = new JComboBox<>(gameTypes);

        top.add(loadButton);
        top.add(saveButton);
        middle.add(type);
        middle.add(gameType);
        bottom.add(Box.createVerticalStrut(50));
        bottom.add(startButton);

        panel.add(top);
        panel.add(middle);
        panel.add(bottom);

        return panel;
    }
}
