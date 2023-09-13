package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class GameLayout implements Serializable {
    private int numberOfCells;
    private CellularAutomataRules rules;
    private Cell[][] cells;

    public GameLayout(int numberOfCells, CellularAutomataRules rules, Cell[][] cells) {
        this.numberOfCells = numberOfCells;
        this.rules = rules;
        this.cells = cells;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public CellularAutomataRules getRules() {
        return rules;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
