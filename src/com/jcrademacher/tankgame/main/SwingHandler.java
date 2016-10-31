package com.jcrademacher.tankgame.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class SwingHandler {

    public static JComboBox<String> gameType;

    public static JButton importButton;
    public static JButton saveButton;
    public static JButton startButton;
    public static JButton loadSpecies;

    public static JLabel fileLabel = new JLabel();

    public static JPanel createController(Main main) {
        JPanel panel = new JPanel();
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        importButton = new JButton("Import Species...");
        saveButton = new JButton("Export Species...");
        startButton = new JButton("Start");
        loadSpecies = new JButton("Load Species");

        loadSpecies.setEnabled(false);
        fileLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        importButton.addActionListener(main);
        saveButton.addActionListener(main);
        startButton.addActionListener(main);
        loadSpecies.addActionListener(main);

        JLabel type = new JLabel("Select game type:");

        String[] gameTypes = {"Human v AI", "Human v Genetic", "Genetic v Genetic", "Genetic v AI", "AI v AI"};

        gameType = new JComboBox<>(gameTypes);

        top.add(importButton);
        top.add(saveButton);
        middle.add(type);
        middle.add(gameType);
        bottom.add(loadSpecies);
        bottom.add(startButton);

        panel.add(top);
        panel.add(fileLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(middle);
        panel.add(Box.createVerticalGlue());
        panel.add(bottom);

        return panel;
    }
}
