package uy.utec.pa.dao;
import uy.utec.pa.modelo.DepartamentoAcademico;
import uy.utec.pa.modelo.Sede;
import uy.utec.pa.util.ConexionBDSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DepaAcademicoDAOImpl implements DepaAcademicoDAO{

    @Override
    public void crearDepaAcademico(DepartamentoAcademico departamentoAcademico){

        String sql = """
        INSERT INTO proyecto.depa_academicos(nombre)
        VALUES (?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, departamentoAcademico.getNombre());
            sentencia.executeUpdate();


            System.out.println("Departamento creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear Departamento: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }

    }

    @Override
    public LinkedList<DepartamentoAcademico> listarDepartamentoAcademico() {

        LinkedList<DepartamentoAcademico> departamentoAcademicos = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.depa_academicos";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_departamento = resultado.getInt("id_departamento");
                String nombre = resultado.getString("nombre");
                DepartamentoAcademico departamentoAcademico = new DepartamentoAcademico(nombre, id_departamento);
                departamentoAcademicos.add(departamentoAcademico);

            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar posgrados.", e
            );
        }
        return departamentoAcademicos;
    }


    @Override
    public void editarDepaAcademico(DepartamentoAcademico departamentoAcademico){

        String sql = """
        UPDATE proyecto.depa_academicos
        SET nombre = ?
        WHERE  id_departamento = ?     
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, departamentoAcademico.getNombre());
            sentencia.setInt(2,departamentoAcademico.getId_DepartamentoAcademico());
            sentencia.executeUpdate();


            System.out.println("Departamento editado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al editar Departamento: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }

    }


    @Override
    public void eliminarDepaAcademico(int idDepaAcademico){
        String sql = """
        DELETE FROM  proyecto.depa_academicos
        WHERE  id_departamento = ?     
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1,idDepaAcademico);
            sentencia.executeUpdate();


            System.out.println("Departamento eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar Departamento: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
    }


}
