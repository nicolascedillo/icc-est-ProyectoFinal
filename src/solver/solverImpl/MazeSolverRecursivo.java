package solver.solverImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import models.Cell;
import models.CellState;
import models.SolveResult;
import solver.MazeSolver;

public class MazeSolverRecursivo implements MazeSolver {

     private Set<Cell> celdasVisitadas;
    private List<Cell> caminoEncontrado;

    public MazeSolverRecursivo() {
        this.celdasVisitadas = new LinkedHashSet<>();
        this.caminoEncontrado = new ArrayList<>();
    }

    @Override
    public SolveResult getPath(Cell[][] matriz, Cell celdaInicio, Cell celdaFin) {
        this.celdasVisitadas.clear();
        this.caminoEncontrado.clear();
        buscarCamino(matriz, celdaInicio.getFila(), celdaInicio.getColumna(), celdaFin);
        return new SolveResult(new ArrayList<>(this.celdasVisitadas), new ArrayList<>(this.caminoEncontrado));
    }

    private boolean buscarCamino(Cell[][] matriz, int fila, int columna, Cell objetivo) {
        if (!esValido(matriz, fila, columna))
            return false;
        Cell celdaActual = matriz[fila][columna];
        if (this.celdasVisitadas.contains(celdaActual))
            return false;
        this.celdasVisitadas.add(celdaActual);
        if (celdaActual.equals(objetivo)) {
            this.caminoEncontrado.add(celdaActual);
            return true;
        }
        
        if (buscarCamino(matriz, fila + 1, columna, objetivo)
                || buscarCamino(matriz, fila, columna + 1, objetivo)) {
            this.caminoEncontrado.add(celdaActual);
            return true;
        }
        return false;
    }

    private boolean esValido(Cell[][] matriz, int fila, int columna) {
        return (fila >= 0 && fila < matriz.length &&
                columna >= 0 && columna < matriz[0].length &&
                matriz[fila][columna].getEstado() != CellState.PARED);
    }
}
