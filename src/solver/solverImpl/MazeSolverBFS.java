package solver.solverImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import models.Cell;
import models.CellState;
import models.SolveResult;
import solver.MazeSolver;

public class MazeSolverBFS implements MazeSolver {

    @Override
    public SolveResult getPath(Cell[][] matriz, Cell celdaInicio, Cell celdaFin) {

        int filas = matriz.length;
        int columnas = matriz[0].length;

        boolean[][] visitado = new boolean[filas][columnas];
        HashMap<Cell, Cell> mapaPadres = new HashMap<>();
        LinkedList<Cell> cola = new LinkedList<>();
        ArrayList<Cell> recorrido = new ArrayList<>();

        Cell inicio = matriz[celdaInicio.getFila()][celdaInicio.getColumna()];
        Cell fin = matriz[celdaFin.getFila()][celdaFin.getColumna()];

        cola.add(inicio);
        visitado[inicio.getFila()][inicio.getColumna()] = true;

        int[][] direcciones = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!cola.isEmpty()) {
            Cell actual = cola.poll();
            recorrido.add(actual);

            if (actual.equals(fin)) {
                break;
            }

            for (int[] dir : direcciones) {
                int nuevaFila = actual.getFila() + dir[0];
                int nuevaColumna = actual.getColumna() + dir[1];

                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                    Cell vecino = matriz[nuevaFila][nuevaColumna];
                    if (!visitado[nuevaFila][nuevaColumna] && vecino.getEstado() != CellState.PARED) {
                        visitado[nuevaFila][nuevaColumna] = true;
                        mapaPadres.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        List<Cell> camino = new ArrayList<>();
        Cell actual = fin;
        while (mapaPadres.containsKey(actual)) {
            camino.add(actual);
            actual = mapaPadres.get(actual);
        }
        if (actual.equals(inicio)) {
            camino.add(inicio);
            Collections.reverse(camino);
        } else {
            camino.clear(); 
        }
        return new SolveResult(recorrido, camino);
    }

}