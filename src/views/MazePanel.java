package views;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class MazePanel extends JPanel {

    private int filas;
    private int coluumnas;
    private JButton[][] botones;

    public MazePanel(int filas, int columnas) {
        this.filas = filas;
        this.coluumnas = columnas;
        this.botones = new JButton[filas][columnas];
        setLayout(new GridLayout(filas, columnas));
    }

    // Getters and Setters

    public JButton getButton(int fila, int columna) {
        return this.botones[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColuumnas() {
        return coluumnas;
    }

    public JButton[][] getBotones() {
        return botones;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColuumnas(int coluumnas) {
        this.coluumnas = coluumnas;
    }


    public void setBotones(JButton[][] botones) {
        this.botones = botones;
    }

    
}
