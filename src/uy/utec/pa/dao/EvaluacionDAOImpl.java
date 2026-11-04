package uy.utec.pa.dao;

import uy.utec.pa.modelo.Evaluacion;
import uy.utec.pa.modelo.EvaluadorExterno;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.util.LinkedList;

public class EvaluacionDAOImpl implements EvaluacionDAO {

    @Override
    public void crearEvaluacion(Evaluacion evaluacion) {
        // 1. Consulta para averiguar cuál es el ID de evaluación más alto actual y sumarle 1
        String sqlMaxId = "SELECT COALESCE(MAX(id_evaluacion), 0) + 1 FROM proyecto.evaluaciones";

        String sqlInsert = """
        INSERT INTO proyecto.evaluaciones(id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

        String sqlInsert2 = """
        INSERT INTO proyecto.son_asignadas(id_postulacion, id_usuario )
        VALUES (?, ?)
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar()) {

            // Paso A: Calculamos el ID automáticamente
            int siguienteId = 0;
            try (PreparedStatement sentenciaMax = conexion.prepareStatement(sqlMaxId);
                 ResultSet rs = sentenciaMax.executeQuery()) {
                if (rs.next()) {
                    siguienteId = rs.getInt(1);
                }
            }

            // Paso B: Insertamos la evaluación usando ese ID calculado
            try (PreparedStatement sentencia2 = conexion.prepareStatement(sqlInsert2)) {
                sentencia2.setInt(1, evaluacion.getId_Postulacion());
                sentencia2.setInt(2,evaluacion.getEvaluadorExterno().getId_usuario());

                sentencia2.executeUpdate();
                System.out.println("Evaluador asignado con éxito a postulacion.");
            }

            try (PreparedStatement sentencia = conexion.prepareStatement(sqlInsert)) {
                sentencia.setInt(1, siguienteId); // ID calculado automáticamente
                sentencia.setString(2, evaluacion.getNombre());
                sentencia.setDouble(3, evaluacion.getPuntaje_total());
                sentencia.setString(4, evaluacion.getJuicio_global());
                sentencia.setString(5, evaluacion.getComentario_evaluador());
                sentencia.setString(6, "En evaluación"); // Cumpliendo con el CU08
                sentencia.setInt(7, evaluacion.getEvaluadorExterno().getId_usuario());   // id_usuario (Evaluador 1)
                sentencia.setInt(8, evaluacion.getId_Postulacion());                     // id_postulacion
                // id_usuario2 (Evaluador 2)

                sentencia.executeUpdate();
                System.out.println("Evaluación creada con éxito. ID asignado automáticamente: " + siguienteId);
            }

        } catch (SQLException e) {
            System.out.println("Error al crear evaluación: " + e.getMessage());
        }
    }




    @Override
        public void editarEvaluacion(Evaluacion evaluacion) {
            String sql = """
        UPDATE proyecto.evaluaciones
        SET nombre = ?,
            pun_total = ?,
            jui_global = ?,
            com_evaluador = ?,
            estado = ?,
            id_usuario = ?
        WHERE id_evaluacion = ?
        """;

            try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                 PreparedStatement sentencia = conexion.prepareStatement(sql)) {

                sentencia.setString(1, evaluacion.getNombre());
                sentencia.setDouble(2, evaluacion.getPuntaje_total());
                sentencia.setString(3, evaluacion.getJuicio_global());
                sentencia.setString(4, evaluacion.getComentario_evaluador());
                sentencia.setString(5, evaluacion.getEstado());
                sentencia.setInt(6, evaluacion.getEvaluadorExterno().getId_usuario());
                sentencia.setInt(7, evaluacion.getId_Evaluacion());

                int filasActualizadas = sentencia.executeUpdate();

                if (filasActualizadas == 0) {
                    throw new IllegalStateException("No se encontró la evaluación a editar.");
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Error al editar la evaluación.", e);
            }
        }

        @Override
        public void eliminarEvaluacion(int id_Evaluacion) {
            String sql = """
        DELETE FROM proyecto.evaluaciones
        WHERE id_evaluacion = ?
        """;

            try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                 PreparedStatement sentencia = conexion.prepareStatement(sql)) {

                sentencia.setInt(1, id_Evaluacion);
                sentencia.executeUpdate();
                System.out.println("¡Evaluación eliminada correctamente!");

            } catch (SQLException e) {
                throw new IllegalStateException("Error al eliminar Evaluación.", e);
            }
        }

    @Override
    public LinkedList<Evaluacion> listarEvaluacion() {
        LinkedList<Evaluacion> evaluaciones = new LinkedList();

        String sql = "SELECT id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion FROM proyecto.evaluaciones";

        String sqlEvaluador = """
        SELECT u.id_usuario, u.correo, u.nombre, u.apellido, u.calle, u.nacionalidad, 
               u.ci, u.contrasena, u.estado, u.rol, e.institucion
        FROM proyecto.usuarios u
        JOIN proyecto.eva_externos e ON u.id_usuario = e.id_usuario
        WHERE u.id_usuario = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                int idEvaluacion = resultado.getInt("id_evaluacion");
                String nombre = resultado.getString("nombre");
                double puntaje_total = resultado.getDouble("pun_total");
                String juiGlobal = resultado.getString("jui_global");
                String comentario_evaluador = resultado.getString("com_evaluador");
                String estado = resultado.getString("estado");

                // Nombres exactos de las columnas de tu script SQL
                int idUsuario1 = resultado.getInt("id_usuario");
                int idPostulacion = resultado.getInt("id_postulacion");

                // 1. Cargar Evaluador 1
                EvaluadorExterno evaluador1 = null;
                if (idUsuario1 > 0) {
                    try (PreparedStatement sentenciaEval1 = conexion.prepareStatement(sqlEvaluador)) {
                        sentenciaEval1.setInt(1, idUsuario1);
                        try (ResultSet rs1 = sentenciaEval1.executeQuery()) {
                            if (rs1.next()) {
                                evaluador1 = new EvaluadorExterno(
                                        rs1.getString("correo"), rs1.getString("nombre"), rs1.getString("apellido"),
                                        rs1.getString("calle"), rs1.getString("nacionalidad"), rs1.getString("ci"),
                                        rs1.getString("contrasena"), rs1.getString("estado"), rs1.getString("rol"),
                                        rs1.getString("institucion")
                                );
                                evaluador1.setId_usuario(rs1.getInt("id_usuario"));
                            }
                        }
                    }
                }



                Evaluacion evaluacion = new Evaluacion(
                        idPostulacion, evaluador1, comentario_evaluador, puntaje_total, juiGlobal, estado, nombre, idEvaluacion
                );

                evaluaciones.add(evaluacion);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al listar evaluaciones.", e);
        }

        return evaluaciones;
    }

    @Override
    public LinkedList<EvaluadorExterno> obtenerEvaluadoresPorEvaluacion(int idEvaluacion) {
        LinkedList<EvaluadorExterno> evaluadores = new LinkedList<>();

        String sql = """
        SELECT u.id_usuario, u.correo, u.nombre, u.apellido, u.calle, 
               u.nacionalidad, u.ci, u.contrasena, u.estado, u.rol, e.institucion
        FROM proyecto.evaluaciones ev
        INNER JOIN proyecto.usuarios u 
            ON u.id_usuario = ev.id_usuario
        INNER JOIN proyecto.eva_externos e 
            ON u.id_usuario = e.id_usuario
        WHERE ev.id_evaluacion = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idEvaluacion);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {

                    EvaluadorExterno evaluador = new EvaluadorExterno(
                            resultado.getString("correo"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("calle"),
                            resultado.getString("nacionalidad"),
                            resultado.getString("ci"),
                            resultado.getString("contrasena"),
                            resultado.getString("estado"),
                            resultado.getString("rol"),
                            resultado.getString("institucion")
                    );
                    evaluador.setId_usuario(resultado.getInt("id_usuario"));

                    evaluadores.add(evaluador);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al obtener los evaluadores de la evaluación.", e);
        }

        return evaluadores;
    }


    @Override
    public int obtenerIdEvaluacionPorPostulacion(int idPostulacion) {

        String sql = """
        SELECT id_evaluacion
        FROM proyecto.evaluaciones
        WHERE id_postulacion = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPostulacion);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return resultado.getInt("id_evaluacion");
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al obtener la evaluación de la postulación.", e
            );
        }

        return -1; // No existe evaluación para esa postulación
    }


    @Override
    public int verificarEvaluadores(int idPostulacion) {

        String sql = """
        SELECT COUNT(*) AS cantidad
        FROM proyecto.son_asignadas
        WHERE id_postulacion = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             sentencia.setInt(1,idPostulacion);
             ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                int cantidad = resultado.getInt("cantidad");
                return cantidad;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al verificar evaluadores asignados.", e
            );
        }

        return 0;
    }

    @Override
    public int verificarEvaluadoresEvaluaron(int idPostulacion) {

        String sql = """
        SELECT COUNT(DISTINCT v.id_usuario) AS cantidad
        FROM proyecto.evaluaciones e
        INNER JOIN proyecto.valoraciones v
            ON v.id_evaluacion = e.id_evaluacion
        WHERE e.id_postulacion = ?
        """;

        try {
            Connection conexion =
                    ConexionBDSingleton.getInstancia().conectar();

            PreparedStatement sentencia =
                    conexion.prepareStatement(sql);

            sentencia.setInt(1, idPostulacion);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("cantidad");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al verificar evaluadores que realizaron la evaluación.",
                    e
            );
        }

        return 0;
    }




}





