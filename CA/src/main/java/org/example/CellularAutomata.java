package org.example;

import java.util.ArrayList;
import java.util.List;

public class CellularAutomata {

    private Cell[][] state;
    private CellularAutomataRules rules;

    public CellularAutomata(CellularAutomataRules rules, Cell[][] initialState) {
        this.state = initialState.clone();
        this.rules = rules;
    }

    public void iterate() {
        Cell[][] newState = state.clone();

        for (Cell[] row : state) {
            for (Cell cell : row) {
                newState[cell.getX()][cell.getY()] = rules.applyRule(cell
                        , getSurroundingsOfCell(cell));
            }
        }
    }

    // môžme vracať string alebo rovno printovat táto funkcia robí obe
    public StringBuilder printBoard() {
        StringBuilder res = new StringBuilder();
        for (Cell[] row : state) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.WHITE) {
                    res.append('A');
                } else {
                    res.append('D');
                }
            }
            res.append('\n');
        }
        res.append("---------------");
        res.append('\n');
        System.out.print(res);
        return res;
    }

    private List<Cell> getSurroundingsOfCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
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

}
