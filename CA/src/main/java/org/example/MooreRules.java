package org.example;

import java.util.List;

public class MooreRules implements CellularAutomataRules {
    @Override
    public Cell applyRule(Cell cell, List<Cell> surroundings) {
        Cell res = new Cell(CellState.BLACK, cell.getX(), cell.getY());
        long aliveNeighbours = surroundings.stream().filter(x -> x.getCellState() == CellState.WHITE).count();
        if (cell.getCellState() == CellState.WHITE) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                res.setCellState(CellState.WHITE);
            }
        } else if (aliveNeighbours == 3) {
            res.setCellState(CellState.WHITE);
        }
        return res;
    }
}
