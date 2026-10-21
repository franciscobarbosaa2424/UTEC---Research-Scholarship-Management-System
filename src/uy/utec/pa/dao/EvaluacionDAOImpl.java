package uy.utec.pa.dao;

import uy.utec.pa.modelo.Evaluacion;
import uy.utec.pa.modelo.EvaluadorExterno;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.util.LinkedList;

public class EvaluacionDAOImpl implements EvaluacionDAO {
/*
    @Override
    public void crearEvaluacion(Evaluacion evaluacion){
        String sql = """
        INSERT INTO proyecto.evaluaciones(nombre, pun_total, jui_global, com_evaluador, estado, id_usuario_1, id_usuario_2, id_postulacion)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, evaluacion.getNombre());
            sentencia.setDouble(2, evaluacion.getPuntaje_total());
            sentencia.setString(3, evaluacion.getJuicio_global());
            sentencia.setString(4, evaluacion.getComentario_evaluador());

            // Según el CU08, al asignar ambos evaluadores avanza a "En evaluación"
            sentencia.setString(5, "En evaluación");
            sentencia.setInt(6, evaluacion.getEvaluadorExterno().getId_usuario()); // ID Evaluador 1
            sentencia.setInt(7, evaluacion.getEvaluadorExterno2().getId_usuario()); // ID Evaluador 2
            sentencia.setInt(8, evaluacion.getId_Postulacion()); // ID Postulación

            sentencia.executeUpdate();

            System.out.println("Evaluación creada y evaluadores asignados correctamente. Estado: En evaluación.");

        } catch (SQLException e) {
            System.out.println("Error al crear evaluación: " + e.getMessage());
        }
    }


        }

            @Override
            public void editarEvaluacion(Evaluacion evaluacion){
                String sql = """
                    UPDATE proyecto.evaluaciones
                    SET nombre = ?,
                        pun_total = ?,
                        jui_global = ?,
                        com_evaluador = ?,
                        estado = ?,
                        id_usuario_1 = ?,
                        id_usuario_2 = ?
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
                    sentencia.setInt(7, evaluacion.getEvaluadorExterno2().getId_usuario());
                    sentencia.setInt(8, evaluacion.getId_Evaluacion());

                    int filasActualizadas = sentencia.executeUpdate();

                    if (filasActualizadas == 0) {
                        throw new IllegalStateException("No se encontró la evaluación a editar.");
                    }

                } catch (SQLException e) {
                    throw new IllegalStateException("Error al editar la evaluación.", e);
                }
            }



        }

        @Override
        public void eliminarEvaluacion(int id_Evaluacion){
            String sql = """
        DELETE FROM proyecto.evaluaciones
        WHERE id_evaluacion = ?
        """;

            try {
                Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1,id_Evaluacion);
                sentencia.executeUpdate();

                System.out.println("Evaluacion eliminado correctamente!");
            } catch (SQLException e) {
                throw new IllegalStateException("Error al eliminar Evaluacion.", e);
            }

        }


@Override
public LinkedList<Evaluacion> listarEvaluacion(Evaluacion evaluacion) {
    LinkedList<Evaluacion> evaluaciones = new LinkedList<>();

    // Traemos las columnas de la evaluación junto con los IDs de los dos evaluadores
    String sql = "SELECT id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario_1, id_usuario_2, id_postulacion FROM proyecto.evaluaciones";

    // Query para reconstruir el objeto EvaluadorExterno uniendo la tabla usuarios y eva_externos
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
            int idUsuario1 = resultado.getInt("id_usuario_1");
            int idUsuario2 = resultado.getInt("id_usuario_2");
            int idPostulacion = resultado.getInt("id_postulacion");

            // 1. Buscamos y armamos el EvaluadorExterno 1
            EvaluadorExterno evaluador1 = null;
            if (idUsuario1 > 0) {
                try (PreparedStatement sentenciaEval1 = conexion.prepareStatement(sqlEvaluador)) {
                    sentenciaEval1.setInt(1, idUsuario1);
                    try (ResultSet resultadoEval1 = sentenciaEval1.executeQuery()) {
                        if (resultadoEval1.next()) {
                            evaluador1 = new EvaluadorExterno(
                                    resultadoEval1.getString("correo"),
                                    resultadoEval1.getString("nombre"),
                                    resultadoEval1.getString("apellido"),
                                    resultadoEval1.getString("calle"),
                                    resultadoEval1.getString("nacionalidad"),
                                    resultadoEval1.getString("ci"),
                                    resultadoEval1.getString("contrasena"),
                                    resultadoEval1.getString("estado"),
                                    resultadoEval1.getString("rol"),
                                    resultadoEval1.getString("institucion")
                            );
                            evaluador1.setId_usuario(resultadoEval1.getInt("id_usuario"));
                        }
                    }
                }
            }

            // 2. Buscamos y armamos el EvaluadorExterno 2
            EvaluadorExterno evaluador2 = null;
            if (idUsuario2 > 0) {
                try (PreparedStatement sentenciaEval2 = conexion.prepareStatement(sqlEvaluador)) {
                    sentenciaEval2.setInt(1, idUsuario2);
                    try (ResultSet resultadoEval2 = sentenciaEval2.executeQuery()) {
                        if (resultadoEval2.next()) {
                            evaluador2 = new EvaluadorExterno(
                                    resultadoEval2.getString("correo"),
                                    resultadoEval2.getString("nombre"),
                                    resultadoEval2.getString("apellido"),
                                    resultadoEval2.getString("calle"),
                                    resultadoEval2.getString("nacionalidad"),
                                    resultadoEval2.getString("ci"),
                                    resultadoEval2.getString("contrasena"),
                                    resultadoEval2.getString("estado"),
                                    resultadoEval2.getString("rol"),
                                    resultadoEval2.getString("institucion")
                            );
                            evaluador2.setId_usuario(resultadoEval2.getInt("id_usuario"));
                        }
                    }
                }
            }

            Evaluacion evaluacion = new Evaluacion(
                    idPostulacion, evaluador2, evaluador1, comentario_evaluador, puntaje_total, juiGlobal, estado, nombre, idEvaluacion
            );

            evaluaciones.add(evaluacion);
        }

    } catch (SQLException e) {
        throw new IllegalStateException("Error al listar evaluaciones.", e);
    }

    return evaluaciones;
}*/

    @Override
    public void crearEvaluacion(Evaluacion evaluacion){
        String sql = """
    INSERT INTO proyecto.evaluaciones(id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_usuario2, id_postulacion)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, evaluacion.getId_Evaluacion()); // <-- Acá pasamos el ID de la evaluación
            sentencia.setString(2, evaluacion.getNombre());
            sentencia.setDouble(3, evaluacion.getPuntaje_total());
            sentencia.setString(4, evaluacion.getJuicio_global());
            sentencia.setString(5, evaluacion.getComentario_evaluador());
            sentencia.setString(6, "En evaluación"); // Cumpliendo con el CU08
            sentencia.setInt(7, evaluacion.getEvaluadorExterno().getId_usuario());
            sentencia.setInt(8, evaluacion.getEvaluadorExterno2().getId_usuario());
            sentencia.setInt(9, evaluacion.getId_Postulacion());

            sentencia.executeUpdate();
            System.out.println("Evaluación creada y evaluadores asignados correctamente. Estado: En evaluación.");

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
            id_usuario_1 = ?,
            id_usuario_2 = ?
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
                sentencia.setInt(7, evaluacion.getEvaluadorExterno2().getId_usuario());
                sentencia.setInt(8, evaluacion.getId_Evaluacion());

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
            LinkedList<Evaluacion> evaluaciones = new LinkedList<>();

            String sql = "SELECT id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario_1, id_usuario_2, id_postulacion FROM proyecto.evaluaciones";

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
                    int idUsuario1 = resultado.getInt("id_usuario_1");
                    int idUsuario2 = resultado.getInt("id_usuario_2");
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

                    // 2. Cargar Evaluador 2
                    EvaluadorExterno evaluador2 = null;
                    if (idUsuario2 > 0) {
                        try (PreparedStatement sentenciaEval2 = conexion.prepareStatement(sqlEvaluador)) {
                            sentenciaEval2.setInt(1, idUsuario2);
                            try (ResultSet rs2 = sentenciaEval2.executeQuery()) {
                                if (rs2.next()) {
                                    evaluador2 = new EvaluadorExterno(
                                            rs2.getString("correo"), rs2.getString("nombre"), rs2.getString("apellido"),
                                            rs2.getString("calle"), rs2.getString("nacionalidad"), rs2.getString("ci"),
                                            rs2.getString("contrasena"), rs2.getString("estado"), rs2.getString("rol"),
                                            rs2.getString("institucion")
                                    );
                                    evaluador2.setId_usuario(rs2.getInt("id_usuario"));
                                }
                            }
                        }
                    }

                    // Usamos el constructor de tu clase Evaluacion
                    Evaluacion evaluacion = new Evaluacion(
                            idPostulacion, evaluador2, evaluador1, comentario_evaluador, puntaje_total, juiGlobal, estado, nombre, idEvaluacion
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
            ON u.id_usuario = ev.id_usuario_1 OR u.id_usuario = ev.id_usuario_2
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




}





