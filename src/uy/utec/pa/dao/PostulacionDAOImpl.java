package uy.utec.pa.dao;

import uy.utec.pa.modelo.*;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public abstract class PostulacionDAOImpl implements PostulacionDAO {

    @Override
    public int crearPostulacion(Postulacion postulacion) {
        String sql = """
        INSERT INTO proyecto.postulaciones (
            nombre, can_mes, linea_investigacion, fecha_solicitud, 
            observaciones_ces, grupo_investigacion, orden_final_ces, 
            id_departamento, id_postulante, id_sede
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        int idGenerado = 0;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, postulacion.getNombre_postulacion());
            sentencia.setInt(2, postulacion.getCantidad_meses());
            sentencia.setString(3, postulacion.getLinea_Investigacion());
            sentencia.setDate(4, java.sql.Date.valueOf(postulacion.getFecha_solicitud()));
            sentencia.setString(5, postulacion.getObservacion_CES());
            sentencia.setString(6, postulacion.getOrden_precalculado());
            sentencia.setString(7, postulacion.getOrden_final());
            sentencia.setInt(8, postulacion.getDepartamentoAcademico().getId_DepartamentoAcademico());
            sentencia.setInt(9, postulacion.getPostulante().getId_Postulante());
            sentencia.setInt(10, postulacion.getSede().getId_sede());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    idGenerado = resultado.getInt(1);
                }
            }

            System.out.println("Postulación creada correctamente con ID: " + idGenerado);

        } catch (SQLException e) {
            System.out.println("Error al crear la postulación: " + e.getMessage());
        }

        return idGenerado;
    }

    // READ
    @Override
    public LinkedList<Postulacion> listarPostulaciones() {

        LinkedList<Postulacion> postulaciones = new LinkedList<>();
        String sql = """
    SELECT id_postulacion, nombre, can_mes, linea_investigacion, fecha_solicitud, 
           observaciones_ces, grupo_investigacion, orden_final_ces, 
           id_departamento, id_postulante, id_sede 
    FROM proyecto.postulaciones
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {


                Postulacion postulacion = new Postulacion();
                postulaciones.add(postulacion);

                postulacion.setId_postulacion(resultado.getInt("id_postulacion"));
                postulacion.setNombre_postulacion(resultado.getString("nombre"));
                postulacion.setCantidad_meses(resultado.getInt("can_mes"));
                postulacion.setLinea_Investigacion(resultado.getString("linea_investigacion"));
                postulacion.setFecha_solicitud(resultado.getDate("fecha_solicitud").toLocalDate());
                postulacion.setObservacion_CES(resultado.getString("observaciones_ces"));
                postulacion.setOrden_precalculado(resultado.getString("grupo_investigacion"));
                postulacion.setOrden_final(resultado.getString("orden_final_ces"));


            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al buscar postulaciones.", e);
        }

        return postulaciones;
    }

    // UPDATE
    @Override
    public void editarPostulacion(Postulacion postulacion) {
        String sql = """
    UPDATE proyecto.postulaciones
    SET nombre = ?, 
        can_mes = ?, 
        linea_investigacion = ?, 
        fecha_solicitud = ?, 
        observaciones_ces = ?, 
        grupo_investigacion = ?, 
        orden_final_ces = ?, 
        id_departamento = ?, 
        id_postulante = ?, 
        id_sede = ?
    WHERE id_postulacion = ?
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, postulacion.getNombre_postulacion());
            sentencia.setInt(2, postulacion.getCantidad_meses());
            sentencia.setString(3, postulacion.getLinea_Investigacion());
            sentencia.setDate(4, java.sql.Date.valueOf(postulacion.getFecha_solicitud()));
            sentencia.setString(5, postulacion.getObservacion_CES());
            sentencia.setString(6, postulacion.getOrden_precalculado());
            sentencia.setString(7, postulacion.getOrden_final());
            sentencia.setInt(8, postulacion.getDepartamentoAcademico().getId_DepartamentoAcademico());
            sentencia.setInt(9, postulacion.getPostulante().getId_Postulante());
            sentencia.setInt(10, postulacion.getSede().getId_sede());
            sentencia.setInt(11, postulacion.getId_postulacion());

            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar postulación.", e);
        }
    }

    // DELETE
    @Override
    public void eliminarPostulacion(int id_postulacion) {
        String sql = """
    DELETE FROM proyecto.postulaciones
    WHERE id_postulacion = ?
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id_postulacion);
            sentencia.executeUpdate();

            System.out.println("¡Postulación eliminada correctamente!");

        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar postulación.", e);
        }
    }

}
