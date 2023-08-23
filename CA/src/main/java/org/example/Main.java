package org.example;

public class Main {
    public static void main(String[] args) {
        Cell[][] initalState = new Cell[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                initalState[i][j] = new Cell(CellState.BLACK, i, j);
            }
        }
        
        initalState[3][3].setCellState(CellState.WHITE);
        initalState[3][2].setCellState(CellState.WHITE);
        initalState[2][3].setCellState(CellState.WHITE);

        // tie rules by bolo prepísať nech netreba robiť inštanciu
        CellularAutomataRules rules = new MooreRules();
        CellularAutomata ca = new CellularAutomata(rules, initalState);
        ca.printBoard();
        ca.iterate();
        ca.printBoard();
        ca.iterate();
        ca.printBoard();
        ca.iterate();
        ca.printBoard();
        ca.iterate();
    }
}