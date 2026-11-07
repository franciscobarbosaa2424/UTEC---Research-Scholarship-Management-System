package uy.utec.pa.dao;

import uy.utec.pa.modelo.Convocatoria;
import uy.utec.pa.modelo.Rubrica;

import java.util.LinkedList;

public interface ConvocatoriaDAO {

    void crearConvocatoria(Convocatoria convocatoria);
    LinkedList<Convocatoria> listarConvocatorias();
    LinkedList<Convocatoria> listarConvocatoriaNoCompletadas();
    void editarConvocatoria(Convocatoria convocatoria);
    int buscarConvocatoriaPorNombre(String nombreConvocatoria);
    void darDeBajaConvocatoria(int idConvocatoria);
    int traerIdRubrica (int id_convocatoria);
}
