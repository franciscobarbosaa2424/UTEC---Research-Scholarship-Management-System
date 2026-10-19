package uy.utec.pa.dao;
import uy.utec.pa.modelo.Sede;

import java.util.LinkedList;

public interface SedeDAO {
    int crearSede(Sede sede);
    void editarSede(Sede sede);
    LinkedList<Sede> listarSede();
    void eliminarSede(int idSede);
}
