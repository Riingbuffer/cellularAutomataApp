package org.example.GUI;

import org.example.Board;
import org.example.CellularAutomataRules;
import org.example.MooreRules;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateLayout {
    private JTextField fileName;
    private JFormattedTextField numberOfCells;
    private JComboBox rulesBox;
    private JButton cancelButton;
    private JButton okButton;
    private JLabel fileNameText;
    private JLabel cellsNumberText;
    private JLabel rulesText;
    private JPanel mainPanel;
    private CellularAutomataRules mooreRules;
    private JFrame frame;
    private StartScreen startScreen;

    public CreateLayout(StartScreen startScreen) {
        this.startScreen = startScreen;

        this.frame = new JFrame();
        this.frame.setContentPane(this.mainPanel);
        this.frame.setTitle("Create layout");
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.frame.setSize(new Dimension(1000, 300));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        cancelButton.addActionListener(e -> {
            this.startScreen.setVisibility(true);
            frame.dispose();
        });

        okButton.addActionListener(e -> {
            String name = fileName.getText();
            if (name == null) {
                fileName.setBackground(new Color(255, 153, 153));
            }
            int cellsNumber = getNumberOfCells();
            if (cellsNumber == -1) {
                numberOfCells.setBackground(new Color(255, 153, 153));
                return;
            }
            numberOfCells.setBackground(Color.WHITE);
            int boxChoice = rulesBox.getSelectedIndex();

            switch (boxChoice) {
                case 0:
                    mooreRules = new MooreRules();
                    break;
                default:
            }

            new CreateLayoutScreen(name, cellsNumber, mooreRules, startScreen);
            frame.dispose();
        });
    }

    private void createUIComponents() {
        this.numberOfCells = new JFormattedTextField(NumberFormat.getNumberInstance());
    }

    public int getNumberOfCells() {
        Number input = (Number)numberOfCells.getValue();
        if (input == null) {
            return -1;
        }
        return input.intValue();
    }
}
