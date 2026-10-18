package uy.utec.pa.dao;

import uy.utec.pa.modelo.Postulante;

public interface PostulanteDAO {
    void crearPostulante(Postulante postulante);
    int buscarPostulantePorDocumento(String documento);
    //void editarPostulante(Postulante postulante);
}
