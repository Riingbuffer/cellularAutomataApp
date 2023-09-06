package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {
    private final String fileName;
    private int cellsNumber;
    private int rulesAtSelectedIndex;
    CellularAutomataRules rules;

    public FileInput(String fileName) throws FileNotFoundException {
        this.fileName = "C:\\Users\\macik\\Desktop\\UNIZA\\java\\cellularAutomat\\CA\\files\\" + fileName + ".txt";
        this.cellsNumber = 0;
        this.rulesAtSelectedIndex = 0;
        this.read();
    }

    public CellularAutomata read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(this.fileName));
        this.cellsNumber = scanner.nextInt();
        this.rulesAtSelectedIndex = scanner.nextInt();

        CellularAutomataRules rules = new MooreRules();
        CellularAutomata ca = new CellularAutomata(rules, cellsNumber, cellsNumber);


        while (scanner.hasNext()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            ca.setState(x, y, CellState.WHITE);
        }
        scanner.close();

        return ca;
    }
}
