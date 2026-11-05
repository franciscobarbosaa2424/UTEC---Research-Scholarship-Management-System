package uy.utec.pa.dao;

import uy.utec.pa.modelo.Valoracion;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValoracionDAOImpl implements ValoracionDAO {

    @Override
    public void crearValoracion(Valoracion valoracion) {

        String sql = """
        
                INSERT INTO proyecto.valoraciones(id_criterio, puntaje, id_evaluacion, id_usuario, id_postulacion)
        VALUES (?,?,?,?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, valoracion.getCriterio().getIdCriterio());
            sentencia.setDouble(2, valoracion.getPuntaje());
            sentencia.setInt(3, valoracion.getIdEvaluacion());
            sentencia.setInt(4, valoracion.getIdUsuario());
            sentencia.setInt(5, valoracion.getIdPostulacion());

            sentencia.executeUpdate();


            System.out.println("Valoracion creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear Valoracion: " + e.getMessage());

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
