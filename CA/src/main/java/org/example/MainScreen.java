package org.example;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private CellularAutomata cellularAutomata;
    private JButton button;
    private boolean running;
    public MainScreen(CellularAutomata cellularAutomata) {

        this.cellularAutomata = cellularAutomata;
        this.running = false;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.add(this.cellularAutomata, BorderLayout.CENTER);
        this.button = new JButton("Start");
        this.add(this.button, BorderLayout.SOUTH);
        this.button.setVisible(true);

        Timer timer = new Timer(100, e1 -> {
            cellularAutomata.iterate();
            cellularAutomata.updateGUI();
        });

        button.addActionListener(e -> {
            if (running) {
                timer.stop();
                this.button.setText("Start");
            } else {
                this.button.setText("Stop");
                timer.start();
            }
            running = !running;
        });

        this.pack();
        this.setVisible(true);
    }
}
