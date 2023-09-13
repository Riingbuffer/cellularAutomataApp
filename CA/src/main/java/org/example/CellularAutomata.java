package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellularAutomata extends JPanel {

    private Cell[][] state;
    private CellularAutomataRules rules;
    private int cellsNumber;
    public CellularAutomata(CellularAutomataRules rules, int cellsNumber) {
        this.cellsNumber = cellsNumber;
        this.state = new Cell[this.cellsNumber][this.cellsNumber];

        for (int i = 0; i < this.cellsNumber; i++) {
            for (int j = 0; j < this.cellsNumber; j++) {
                Cell cell = new Cell(CellState.BLACK, i, j);
                this.state[i][j] = cell;
                this.add(cell);
                state[i][j].setBackground(Color.BLACK);
            }
        }

        this.rules = rules;

        this.setLayout(new GridLayout(this.cellsNumber, this.cellsNumber));
        this.setVisible(true);
    }

    public void iterate() {

        for (Cell[] row : state) {
            for (Cell cell : row) {
                CellState cellState = rules.applyRule(cell, getSurroundingsOfCell(cell));
                if (cellState != cell.getCellState()) {
                    cell.setToBeSwitched(true);
                }
            }
        }

        for (Cell[] row : state) {
            for (Cell cell : row) {
                if (cell.isToBeSwitched()) {
                    cell.setToBeSwitched(false);
                    if (cell.getCellState() == CellState.WHITE) {
                        cell.setCellState(CellState.BLACK);
                    } else {
                        cell.setCellState(CellState.WHITE);
                    }
                }
            }
        }

    }

    // môžme vracať string alebo rovno printovat táto funkcia robí obe
    public StringBuilder printBoard() {
        StringBuilder res = new StringBuilder();
        for (Cell[] row : state) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.WHITE) {
                    res.append('W');
                } else {
                    res.append('B');
                }
            }
            res.append('\n');
        }
        res.append("---------------");
        res.append('\n');
        System.out.print(res);
        return res;
    }

    public void updateGUI() {
        for (Cell[] row : state) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.WHITE) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.BLACK);
                }
                cell.setBorderPainted(false);
            }
        }
    }

    private List<Cell> getSurroundingsOfCell(Cell cell) {
        int x = cell.getXCoord();
        int y = cell.getYCoord();
        List<Cell> res = new ArrayList<>();

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if ((i < 0 || i > state[0].length - 1) || (j < 0 || j > state.length - 1) || (i == x && j == y)) {
                    continue;
                }
                res.add(state[i][j]);
            }
        }
        return res;
    }

    public void copyState(Cell[][] state) {
        for (int i = 0; i < this.cellsNumber; i++) {
            for (int j = 0; j < this.cellsNumber; j++) {
                this.state[j][i].setCellState(state[j][i].getCellState());
            }
        }
    }

}
