package org.example.GUI;

import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StartScreen extends JPanel {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JLabel appName;
    private JPanel southPanel;
    private JFormattedTextField numberOfCells;
    private JComboBox rulesBox;
    private JCheckBox layoutCheckBox;
    private JTextField fileName;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel middlePanel;
    private JPanel panelInNorth;
    private JPanel panelInSouth;
    private JLabel numberOfCellsText;
    private JLabel rulesBoxText;
    private JLabel chooseLayout;
    private JButton createButton;
    private JComboBox layoutBox;
    private JPanel layoutPanel;
    private JButton deleteButton;
    private CellularAutomataRules mooreRules;
    private GameScreen gameScreen;
    private GameLayoutManager gameLayoutManager;
    private JFrame frame;

    public StartScreen() {


        this.frame = new JFrame();
        this.frame.setContentPane(this.mainPanel);
        this.frame.setTitle("Cellular automata");
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.frame.setSize(new Dimension(600, 600));
        this.frame.setResizable(false);
        this.frame.setBackground(new Color(205, 205, 205));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);



        layoutBox.setEnabled(false);
        createButton.setEnabled(false);
        deleteButton.setEnabled(false);

        this.gameLayoutManager = new GameLayoutManager();
        this.refreshLayoutBox();

        okButton.addActionListener(e -> {
            if (layoutCheckBox.isSelected()) {
                chooseLayout();
            } else {
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

                CellularAutomata ca = new CellularAutomata(mooreRules, cellsNumber);
                this.gameScreen = new GameScreen(ca, this);
            }
        });


        cancelButton.addActionListener(e -> System.exit(0) );

        layoutCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                numberOfCells.setEnabled(false);
                rulesBox.setEnabled(false);
                layoutBox.setEnabled(true);
                createButton.setEnabled(true);
                deleteButton.setEnabled(true);
            } else {
                numberOfCells.setEnabled(true);
                rulesBox.setEnabled(true);
                layoutBox.setEnabled(false);
                createButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        createButton.addActionListener(e -> {
            this.setVisibility(false);
            this.setVisible(false);
            new CreateLayout(this);
        });

        deleteButton.addActionListener(e -> {
            if (gameLayoutManager.getGameLayouts().isEmpty()) return;
            int option = JOptionPane.showConfirmDialog(null, "Do you really want to delete this layout?", "Delete layout", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
            if (option == YES_OPTION) {
                String layoutName = (String)layoutBox.getSelectedItem();
                this.removeGameLayout(layoutName);
            }
        });

        this.setVisibility(true);
    }

    private void createUIComponents() {
        this.numberOfCells = new JFormattedTextField(NumberFormat.getNumberInstance());
        this.layoutBox = new JComboBox<String>();
    }

    public void refreshLayoutBox() {
        this.layoutBox.removeAllItems();
        for (String name : gameLayoutManager.getGameLayouts().keySet()) {
            this.layoutBox.addItem(name);
        }
    }


    public void setVisibility(boolean b) {
        this.frame.setVisible(b);
    }


    private void chooseLayout() {
        String layoutName = (String)layoutBox.getSelectedItem();
        GameLayout gameLayout = gameLayoutManager.getGameLayouts().get(layoutName);
        if (gameLayout == null) return;
        CellularAutomata cellularAutomata = new CellularAutomata(gameLayout.getRules(), gameLayout.getNumberOfCells());
        cellularAutomata.copyState(gameLayout.getCells());
        cellularAutomata.updateGUI();
        new GameScreen(cellularAutomata, this);
    }

    public int getNumberOfCells() {
        Number input = (Number)numberOfCells.getValue();
        if (input == null) {
            return -1;
        }
        return input.intValue();
    }

    public void addGameLayout(String key, GameLayout gameLayout) {
        gameLayoutManager.addGameLayout(key, gameLayout);
        refreshLayoutBox();
        gameLayoutManager.saveLayoutToFile();
    }

    public void removeGameLayout(String key) {
        gameLayoutManager.removeFromGameLayouts(key);
        refreshLayoutBox();
    }
}
