package org.example;

import java.io.Serializable;
import java.util.List;

public class MooreRules implements CellularAutomataRules, Serializable {
    @Override
    public CellState applyRule(Cell cell, List<Cell> surroundings) {
        long aliveNeighbours = surroundings.stream().filter(x -> x.getCellState() == CellState.WHITE).count();
        if (cell.getCellState() == CellState.WHITE) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return CellState.WHITE;
            }
        } else if (aliveNeighbours == 3) {
            return CellState.WHITE;
        }
        return CellState.BLACK;
    }
}
