package uy.utec.pa.dao;

import uy.utec.pa.modelo.Criterio;

import java.util.LinkedList;

public interface CriterioDAO {
    void crearCriterios(LinkedList<Criterio> criterios);
    void editarCriterio(Criterio criterio);
    LinkedList<Criterio> listarCriterios(int idSeccion);
}
