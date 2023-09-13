package org.example.GUI;

import org.example.Board;
import org.example.CellularAutomataRules;
import org.example.GameLayout;

import javax.swing.*;
import java.awt.*;

public class CreateLayoutScreen extends JFrame {
    private String name;
    private Board board;
    private CellularAutomataRules rules;
    private int numberOfCells;
    private JButton createButton;
    private StartScreen startScreen;

    public CreateLayoutScreen(String name, int numberOfCells, CellularAutomataRules rules, StartScreen startScreen) {
        this.name = name;
        this.numberOfCells = numberOfCells;
        this.board = new Board(numberOfCells, numberOfCells);
        this.rules = rules;
        this.createButton = new JButton("Create");
        this.startScreen = startScreen;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.add(this.board, BorderLayout.CENTER);
        this.add(this.createButton, BorderLayout.SOUTH);

        this.createButton.setVisible(true);
        this.createButton.addActionListener(e -> {
            GameLayout gameLayout = new GameLayout(this.numberOfCells, this.rules, this.board.getState());
            this.startScreen.addGameLayout(this.name, gameLayout);
            this.startScreen.setVisibility(true);
            this.dispose();
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
