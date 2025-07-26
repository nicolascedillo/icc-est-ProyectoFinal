package models;

public class AlgorithmResult {
    private final String nombreDelAlgoritmo;
    private final int longitudDelCamino;
    private final int tiempo;
    public AlgorithmResult(String nombreDelAlgoritmo, int longitudDelCamino, int tiempo) {
        this.nombreDelAlgoritmo = nombreDelAlgoritmo;
        this.longitudDelCamino = longitudDelCamino;
        this.tiempo = tiempo;
    }

    public String getNombreDelAlgoritmo() {
        return nombreDelAlgoritmo;
    }

    public int getLongitudDelCamino() {
        return longitudDelCamino;
    }

    public int getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Resultado de Algoritmo: " +
                nombreDelAlgoritmo + " , " +
                longitudDelCamino + ", " +
                tiempo;
    }
}
