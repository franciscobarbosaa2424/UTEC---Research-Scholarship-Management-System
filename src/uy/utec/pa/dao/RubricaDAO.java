package uy.utec.pa.dao;

import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.modelo.SeccionRubrica;

import java.util.LinkedList;

public interface RubricaDAO {
    void crearRubrica(Rubrica rubrica);
    int buscarRubricaPorNombre(String nombre);
    LinkedList<Rubrica> listarRubricas();
    LinkedList<SeccionRubrica> listarSecciones(int id_rubrica);
    void editarRubrica(Rubrica rubrica);
    void editarSeccionRubrica(SeccionRubrica seccionRubrica);
    void eliminarRubrica(int id_rubrica);
}
