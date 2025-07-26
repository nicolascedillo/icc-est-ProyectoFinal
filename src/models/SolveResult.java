package models;

import java.util.List;

public class SolveResult {

    private final List<Cell> visitadas;
    private final List<Cell> camino;

    public SolveResult(List<Cell> visitadas, List<Cell> camino) {
        this.visitadas = visitadas;
        this.camino = camino;
    }

    public List<Cell> getVisitadas() {
        return visitadas;
    }

    public List<Cell> getCamino() {
        return camino;
    }

}
