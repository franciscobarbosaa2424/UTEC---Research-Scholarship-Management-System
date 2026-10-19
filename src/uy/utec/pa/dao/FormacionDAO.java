package uy.utec.pa.dao;

import uy.utec.pa.modelo.Formacion;

import java.util.LinkedList;

public interface FormacionDAO {
    int crearFormacion(Formacion formacion);
    void asignarFormacionCarrera(int idFormacion,int idCarrera);
    void asignarFormacionPosgrado(int idFormacion,int idPosgrado);

}
