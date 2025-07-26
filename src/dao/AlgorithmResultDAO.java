package dao;

import java.util.List;

import models.AlgorithmResult;

public interface AlgorithmResultDAO {

    void guardar(AlgorithmResult resultadoAlgoritmo);

    List<AlgorithmResult> encontrarTodos();

    void limpiar();
}
