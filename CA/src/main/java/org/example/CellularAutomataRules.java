package org.example;

import java.util.List;

public interface CellularAutomataRules {
    Cell applyRule(Cell cell, List<Cell> surroundings);
}
