package org.example;

import java.io.Serializable;
import java.util.List;

public interface CellularAutomataRules {
    CellState applyRule(Cell cell, List<Cell> surroundings);
}
