package uy.utec.pa.dao;
import uy.utec.pa.modelo.Formacion;
import uy.utec.pa.util.ConexionBDSingleton;
import java.sql.*;

public class FormacionDAOImpl implements FormacionDAO{

    @Override
    public int crearFormacion(Formacion formacion){
        String sql = """
        INSERT INTO proyecto.formaciones(nom_formacion, id_postulante)
        VALUES (?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        int idGenerado=0;
        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            sentencia.setString(1, formacion.getNombre_formacion());
            sentencia.setInt(2, formacion.getPostulante().getId_Postulante());

            sentencia.executeUpdate();

            resultado = sentencia.getGeneratedKeys();
            if (resultado.next()) {
                idGenerado = resultado.getInt(1);
            }

            System.out.println("formacion creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear formacion: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
        return idGenerado;
    }

    @Override
    public void asignarFormacionCarrera(int idFormacion,int idCarrera){

        String sql = """
        INSERT INTO proyecto.carr_form(id_formacion,id_carrera)
        VALUES (?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, idFormacion);
            sentencia.setInt(2, idCarrera);

            sentencia.executeUpdate();

            System.out.println("Tablas asociadas creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al asociar tablas: " + e.getMessage());

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
    public void asignarFormacionPosgrado(int idFormacion,int idPosgrado){

        String sql = """
        INSERT INTO proyecto.posg_form(id_posgrado, id_formacion)
        VALUES (?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, idPosgrado);
            sentencia.setInt(2, idFormacion);

            sentencia.executeUpdate();

            System.out.println("Tablas asociadas creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al asociar tablas: " + e.getMessage());

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
