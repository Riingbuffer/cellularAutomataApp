package org.example;

public class Main {
    public static void main(String[] args) {

        // tie rules by bolo prepísať nech netreba robiť inštanciu
        CellularAutomataRules rules = new MooreRules();
        CellularAutomata ca = new CellularAutomata(rules, 50, 50);

        new MainScreen(ca);
    }
}