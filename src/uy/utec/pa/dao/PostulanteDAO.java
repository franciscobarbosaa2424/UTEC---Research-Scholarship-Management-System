package uy.utec.pa.dao;

import uy.utec.pa.modelo.Postulante;

import java.util.LinkedList;

public interface PostulanteDAO {
    void crearPostulante(Postulante postulante);
    int buscarPostulantePorDocumento(String documento);
    //void editarPostulante(Postulante postulante);
    LinkedList<Postulante> listarPostulantes();
}

