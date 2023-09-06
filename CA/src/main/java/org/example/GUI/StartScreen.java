package org.example.GUI;

import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.text.NumberFormat;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StartScreen {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JLabel appName;
    private JPanel southPanel;
    private JFormattedTextField numberOfCells;
    private JComboBox rulesBox;
    private JCheckBox readFileCheckBox;
    private JTextField fileName;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel middlePanel;
    private JPanel panelInNorth;
    private JPanel panelInSouth;
    private JLabel numberOfCellsText;
    private JLabel rulesBoxText;
    private JLabel readFileText;

    private CellularAutomataRules mooreRules;
    private MainScreen mainScreen;

    private JFrame frame;

    public StartScreen() {

        this.frame = new JFrame();
        this.frame.setContentPane(this.mainPanel);
        this.frame.setTitle("Cellular automata");
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(600, 600));
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        fileName.setEnabled(false);

        okButton.addActionListener(e -> {
            if (readFileCheckBox.isSelected()) {
                readFile();
            } else {
                Number input = (Number)numberOfCells.getValue();
                int cellsNumber = input.intValue();

                int boxChoice = rulesBox.getSelectedIndex();

                switch (boxChoice) {
                    case 0:
                        mooreRules = new MooreRules();
                        break;
                    default:
                }

                CellularAutomata ca = new CellularAutomata(mooreRules, cellsNumber, cellsNumber);
                this.mainScreen = new MainScreen(ca, this);
            }
        });


        cancelButton.addActionListener(e -> System.exit(0) );

        readFileCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                numberOfCells.setEnabled(false);
                rulesBox.setEnabled(false);
                fileName.setEnabled(true);
            } else {
                numberOfCells.setEnabled(true);
                rulesBox.setEnabled(true);
                fileName.setEnabled(false);
            }
        });
    }

    private void createUIComponents() {
        this.numberOfCells = new JFormattedTextField(NumberFormat.getNumberInstance());
    }

    public void setVisibility(boolean b) {
        this.frame.setVisible(b);
    }

    private void readFile() {
        try {
            FileInput input = new FileInput(this.fileName.getText());
            this.mainScreen = new MainScreen(input.read(), this);
            fileName.setBackground(Color.WHITE);
        } catch (FileNotFoundException e) {
            fileName.setBackground(new Color(255, 153, 153));
            JOptionPane.showMessageDialog(null, "File does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
