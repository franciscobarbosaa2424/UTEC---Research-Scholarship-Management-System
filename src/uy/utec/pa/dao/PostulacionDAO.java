package uy.utec.pa.dao;

import uy.utec.pa.modelo.Postulacion;

import java.util.LinkedList;

public interface PostulacionDAO {

    int crearPostulacion(Postulacion postulacion);
    void editarPostulacion(Postulacion postulacion);
    void eliminarPostulacion(int id_postulacion);
    LinkedList<Postulacion> listarPostulaciones();
    Postulacion buscarPostulacion(int id_postulacion);
}
