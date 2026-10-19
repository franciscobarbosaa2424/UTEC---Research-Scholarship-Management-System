package uy.utec.pa.dao;

import uy.utec.pa.modelo.TutorUtec;

import java.util.LinkedList;

public interface TutorDAO {
    void crearTutor(TutorUtec tutor);
    void editarTutor(TutorUtec tutor);
    void eliminarTutor(int idTutor);
    LinkedList<TutorUtec> listarTutores();
}
