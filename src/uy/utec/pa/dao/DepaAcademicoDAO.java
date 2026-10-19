package uy.utec.pa.dao;
import uy.utec.pa.modelo.DepartamentoAcademico;
import java.util.LinkedList;

public interface DepaAcademicoDAO {

    void crearDepaAcademico(DepartamentoAcademico departamentoAcademico);
    LinkedList<DepartamentoAcademico> listarDepartamentoAcademico();
    void editarDepaAcademico(DepartamentoAcademico departamentoAcademico);
    void eliminarDepaAcademico(int idDepaAcademico);
}
