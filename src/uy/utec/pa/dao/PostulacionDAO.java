package uy.utec.pa.dao;

import uy.utec.pa.modelo.Postulacion;

import java.util.LinkedList;

public interface PostulacionDAO {

    int crearPostulacion(Postulacion postulacion);
    void eliminarPostulacion(int id_postulacion);
    LinkedList<Postulacion> listarPostulaciones();
   // Postulacion buscarPostulacion();
    void editarPostulacion(Postulacion postulacion);
    void actualizarEstadoPostulacion(int idPostulacion, String nuevoEstado);
    LinkedList<Postulacion> listarPostulacionesAsignadas(int id_usuario);
}
