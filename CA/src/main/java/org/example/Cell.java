package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {

    private int xCoord;
    private int yCoord;
    private CellState cellState;
    private boolean toBeSwitched;

    public Cell(CellState cellState, int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
        this.cellState = cellState;
        this.toBeSwitched = false;

        this.addActionListener(this);
        //this.setPreferredSize(new Dimension(75, 75));
        this.requestFocus();
        this.setVisible(true);
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public boolean isToBeSwitched() {
        return toBeSwitched;
    }

    public void setToBeSwitched(boolean toBeSwitched) {
        this.toBeSwitched = toBeSwitched;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.cellState == CellState.WHITE) {
            this.setBackground(Color.BLACK);
            this.cellState = CellState.BLACK;
        } else {
            this.setBackground(Color.WHITE);
            this.cellState = CellState.WHITE;
        }
    }
}
