package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipGUI extends JFrame {
    private JButton[][] buttons; // Felder des Spielfelds

    public BattleShipGUI(int rows, int cols) {
        super("Schiffe versenken");

        // Erzeugen des Hauptfensters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(rows, cols));

        // Initialisieren der Felder
        buttons = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new FieldClickListener(i, j)); // ActionListener hinzufügen
                add(buttons[i][j]); // Button zum Hauptfenster hinzufügen
            }
        }

        pack(); // Größe des Fensters anpassen
        setVisible(true); // Fenster sichtbar machen
    }

    // ActionListener für Felder
    private class FieldClickListener implements ActionListener {
        private int row;
        private int col;

        public FieldClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Klick auf Feld: " + row + ", " + col);
        }
    }

    public static void main(String[] args) {
        // Beispielaufruf der GUI mit einem 10x10 Spielfeld
        SwingUtilities.invokeLater(() -> new BattleshipGUI(10, 10));
    }
}