package uy.utec.pa.dao;

import uy.utec.pa.modelo.Evaluacion;
import uy.utec.pa.modelo.EvaluadorExterno;

import java.util.LinkedList;

public interface EvaluacionDAO {

   void crearEvaluacion(Evaluacion evaluacion);
    void editarEvaluacion(Evaluacion evaluacion);
    LinkedList<Evaluacion> listarEvaluacion();
   void eliminarEvaluacion(int id_Evaluacion);
    LinkedList<EvaluadorExterno> obtenerEvaluadoresPorEvaluacion(int idEvaluacion);
}
