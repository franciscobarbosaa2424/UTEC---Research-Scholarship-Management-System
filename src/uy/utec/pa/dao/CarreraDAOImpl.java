package uy.utec.pa.dao;

import uy.utec.pa.modelo.Carrera;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarreraDAOImpl implements CarreraDAO{

    @Override
    public void crearCarrera(Carrera carrera){
        String sql = """
        INSERT INTO proyecto.carreras(nom_carrera,id_institucion)
        VALUES (?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, carrera.getNombre());
            sentencia.setInt(2,carrera.getInstitucion().getId_Institucion());

            sentencia.executeUpdate();

            System.out.println("Carrera creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear institucion: " + e.getMessage());

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
