package org.example.GUI;

import org.example.CellularAutomata;
import org.example.GUI.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen extends JFrame {
    private CellularAutomata cellularAutomata;
    private JButton buttonStart;
    private JButton buttonBack;
    private JButton buttonContinuePause;
    private boolean running;
    private StartScreen startScreen;
    public MainScreen(CellularAutomata cellularAutomata, StartScreen startScreen) {

        this.cellularAutomata = cellularAutomata;
        this.startScreen = startScreen;
        this.startScreen.setVisibility(false);
        this.cellularAutomata.updateGUI();
        this.running = false;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(this.cellularAutomata, BorderLayout.CENTER);

        //BUTTONS
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        this.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setVisible(true);
        this.buttonStart = new JButton("Start");
        buttonsPanel.add(this.buttonStart);
        this.buttonStart.setVisible(true);

        this.buttonBack = new JButton("Back");
        buttonsPanel.add(this.buttonBack);
        this.buttonBack.setVisible(false);

        this.buttonContinuePause = new JButton("Pause");
        buttonsPanel.add(this.buttonContinuePause);
        this.buttonContinuePause.setVisible(false);
        //

        Timer timer = new Timer(100, e1 -> {
            cellularAutomata.iterate();
            cellularAutomata.updateGUI();
        });

        buttonStart.addActionListener(e -> {
            timer.start();
            this.buttonStart.setVisible(false);
            this.buttonContinuePause.setVisible(true);
            this.buttonBack.setVisible(true);
            running = true;
        });

        buttonBack.addActionListener(e -> {
            if (running) {
                timer.stop();
            }
            this.dispose();
            startScreen.setVisibility(true);
        });

        buttonContinuePause.addActionListener(e -> {
            if (running) {
                timer.stop();
                this.buttonContinuePause.setText("Continue");
            } else {
                this.buttonContinuePause.setText("Pause");
                timer.start();
            }
            running = !running;
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (running) {
                    timer.stop();
                }
            }
        });

        this.pack();
        this.setVisible(true);
    }
}
