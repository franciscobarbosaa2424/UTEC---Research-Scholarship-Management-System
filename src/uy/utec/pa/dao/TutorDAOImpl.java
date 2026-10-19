package uy.utec.pa.dao;

import uy.utec.pa.modelo.SeccionRubrica;
import uy.utec.pa.modelo.TutorUtec;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TutorDAOImpl implements TutorDAO{

    @Override
    public void crearTutor(TutorUtec tutor){
        String sql = """
        INSERT INTO proyecto.tut_utec(cor_utec, nombre, apellido, dep_area)
        VALUES (?,?,?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, tutor.getCorreo_utec());
            sentencia.setString(2, tutor.getNombre());
            sentencia.setString(3, tutor.getApellido());
            sentencia.setString(4, tutor.getDepartamento());

            sentencia.executeUpdate();


            System.out.println("Tutor creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear Tutor: " + e.getMessage());

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
    public void editarTutor(TutorUtec tutor){
        String sql = """
        UPDATE proyecto.tut_utec
        SET cor_utec=?, nombre=?, apellido=?, dep_area=?
        WHERE id_tutor=?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, tutor.getCorreo_utec());
            sentencia.setString(2, tutor.getNombre());
            sentencia.setString(3, tutor.getApellido());
            sentencia.setString(4, tutor.getDepartamento());
            sentencia.setInt(5,tutor.getId_tutor());

            sentencia.executeUpdate();


            System.out.println("Tutor editado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al editar Tutor: " + e.getMessage());

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
    public void eliminarTutor(int idTutor){
        String sql2 = """
        DELETE FROM proyecto.tut_utec
        WHERE id_tutor = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql2);
            sentencia.setInt(1, idTutor);
            sentencia.executeUpdate();

            System.out.println("Se elimino tutor correctamente");
        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar tutor.", e);
        }
    }


    @Override
    public LinkedList<TutorUtec> listarTutores(){
        String sql = "SELECT * FROM proyecto.tut_utec";

        LinkedList<TutorUtec> tutores = new LinkedList<>();


        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();


            while (resultado.next()) {
                int id_tutor = resultado.getInt("id_tutor");
                String nombreTutor = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String departamento = resultado.getString("dep_area");
                String correo_utec = resultado.getString("cor_utec");


                TutorUtec tutorUtec = new TutorUtec(id_tutor, nombreTutor, apellido, departamento, correo_utec);
                tutores.add(tutorUtec);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la tutor.", e
            );
        }
        return tutores;

    }

}
