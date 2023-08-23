package org.example;

public class Cell {

    private int x;
    private int y;
    private CellState cellState;

    public Cell(CellState cellState, int x, int y) {
        this.x = x;
        this.y = y;
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
