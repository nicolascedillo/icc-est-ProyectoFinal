package models;

public class Cell {
    
    private int fila;
    private int columna;
    private CellState estado;

    public Cell(int fila, int columna ) {
        this.fila = fila;
        this.columna = columna;
        this.estado = CellState.VACIO; 
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public CellState getEstado() {
        return estado;
    }

    public void setEstado(CellState estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fila;
        result = prime * result + columna;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (fila != other.fila)
            return false;
        if (columna != other.columna)
            return false;
        return true;
    }

}