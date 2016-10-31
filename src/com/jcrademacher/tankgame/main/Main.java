package com.jcrademacher.tankgame.main;

import com.jcrademacher.tankgame.game.GameDriver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by jackrademacher on 10/29/16.
 */
public class Main implements ActionListener {
    private JFrame mainframe = new JFrame("Tank Game");
    private JPanel content;

    private GameDriver driver;

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        // creates content panel
        content = SwingHandler.createController(this);

        mainframe.setContentPane(content);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setSize(350,200);
        mainframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // starts game with GameDriver, fires actionPerformed using javax.swing.Timer to Game class
        if(src.equals(SwingHandler.startButton)) {
            if(SwingHandler.startButton.getText().equals("Start")) {
                if (driver == null || !driver.getGame().isFrameVisible()) {
                    driver = new GameDriver();
                    driver.start();
                } else {
                    driver.start();
                }
                ((JButton) src).setText("Pause");
            }
            else {
                driver.pause();
                ((JButton) src).setText("Start");
            }
        }

        // handling for import/export species
        if(src.equals(SwingHandler.importButton)) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("SPC (species) files", "spc");
            chooser.setFileFilter(filter);

            // if user chooses file...
            if(chooser.showOpenDialog(mainframe) == JFileChooser.APPROVE_OPTION) {
                File selected = chooser.getSelectedFile();
                // if file has correct extension specified by filter
                if(filter.accept(selected)) {
                    SwingHandler.fileLabel.setText("Selected file: " + selected.getName());
                    SwingHandler.loadSpecies.setEnabled(true);
                }
                else {
                    // else tell user that file is invalid
                    SwingHandler.fileLabel.setText("Invalid file extension");
                }
            }

        }

        if(src.equals(SwingHandler.loadSpecies)) {
            // disposes of game frame
            driver.killAll();
        }
    }
}
