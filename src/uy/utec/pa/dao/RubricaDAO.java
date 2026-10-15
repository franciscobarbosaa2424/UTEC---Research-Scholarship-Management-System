package uy.utec.pa.dao;

import uy.utec.pa.modelo.Rubrica;

public interface RubricaDAO {
    void crearRubrica(Rubrica rubrica);
    int buscarRubricaPorId(String nombre);
}
