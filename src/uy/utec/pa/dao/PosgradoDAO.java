package uy.utec.pa.dao;

import uy.utec.pa.modelo.Posgrado;

import java.util.LinkedList;

public interface PosgradoDAO {
    void crearPosgrado(Posgrado posgrado);
    LinkedList<Posgrado> listarPosgrados();
    void editarPosgrado(Posgrado posgrado);
    void eliminarPosgrado(int id_posgrado);
}
