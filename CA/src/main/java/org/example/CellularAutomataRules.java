package org.example;

import java.util.List;

public interface CellularAutomataRules {
    CellState applyRule(Cell cell, List<Cell> surroundings);
}
