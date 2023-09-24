package org.example;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Cell[][] state;

    public Board(int sizeX, int sizeY) {
        this.state = new Cell[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                Cell cell = new Cell(CellState.BLACK, i, j);
                this.state[i][j] = cell;
                this.add(cell);
                state[i][j].setBackground(Color.BLACK);
            }
        }

        this.setLayout(new GridLayout(sizeX, sizeY));
        this.setVisible(true);
    }

    public Cell[][] getState() {
        return state;
    }
}
