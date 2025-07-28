package dao.daoImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dao.AlgorithmResultDAO;
import models.AlgorithmResult;

public class AlgorithmResultDAOFile implements AlgorithmResultDAO {

    private File file;

    public AlgorithmResultDAOFile(String ruta) {
        this.file = new File(ruta);
    }

    @Override
    public void guardar(AlgorithmResult resultadoAlgoritmo) {
        List<AlgorithmResult> listaDeResultados = encontrarTodos();
        boolean existe = false;

        for (int i = 0; i < listaDeResultados.size(); i++) {

            if (listaDeResultados.get(i).getNombreDelAlgoritmo()
                    .equalsIgnoreCase(resultadoAlgoritmo.getNombreDelAlgoritmo())) {
                listaDeResultados.set(i, resultadoAlgoritmo);
                existe = true;
                break;
            }
        }

        if (!existe) {
            listaDeResultados.add(resultadoAlgoritmo);
        }

        try {

            FileWriter fileWriter = new FileWriter(this.file, false);
            for (AlgorithmResult resultado : listaDeResultados) {
                fileWriter.write(resultado.toString() + "\n");
            }
            fileWriter.close();

        } catch (IOException iOException) {

            System.err.println("Error: " + iOException.getMessage());
        }
    }

    @Override
    public List<AlgorithmResult> encontrarTodos() {

        List<AlgorithmResult> listaDeResultados = new ArrayList<>();
        if (!this.file.exists()) {
            return listaDeResultados;
        }

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                String[] arrayOfString = str.split(",");
                if (arrayOfString.length == 3) {

                    String nombre = arrayOfString[0].trim();
                    int longitud = Integer.parseInt(arrayOfString[1].trim());
                    int tiempo = Integer.parseInt(arrayOfString[2].trim());

                    listaDeResultados.add(new AlgorithmResult(nombre, longitud, tiempo));
                }
            }
            bufferedReader.close();

        } catch (NumberFormatException e) {
            System.err.println("Error en formato de numero: " + e.getMessage());
        } catch (IOException iOException) {
            System.err.println("Error: " + iOException.getMessage());
        }
        return listaDeResultados;
    }

    @Override
    public void limpiar() {
        try {
            FileWriter fileWriter = new FileWriter(this.file, false);
            fileWriter.close();
        } catch (IOException iOException) {
            System.err.println("Error al limpiar el archivo: " + iOException.getMessage());
        }
    }
}
