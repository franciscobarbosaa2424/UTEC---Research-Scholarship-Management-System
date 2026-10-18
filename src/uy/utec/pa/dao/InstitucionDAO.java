package uy.utec.pa.dao;

import uy.utec.pa.modelo.Institucion;

import java.util.LinkedList;

public interface InstitucionDAO {
    LinkedList<Institucion> listarInstituciones();
    void crearInstitucion(Institucion institucion);
    void editarInstitucion(Institucion institucion);
}
