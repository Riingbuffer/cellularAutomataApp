package org.example;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private CellularAutomata cellularAutomata;
    private JButton startButton;
    public MainScreen(CellularAutomata cellularAutomata) {

        this.cellularAutomata = cellularAutomata;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.add(this.cellularAutomata, BorderLayout.CENTER);
        this.startButton = new JButton("Start");
        this.add(this.startButton, BorderLayout.SOUTH);
        this.startButton.setVisible(true);

        Timer timer = new Timer(100, e1 -> {
            cellularAutomata.iterate();
            cellularAutomata.invalidate();
            cellularAutomata.updateGUI();
        });

        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            timer.start();
        });

        this.pack();
        this.setVisible(true);
    }
}
