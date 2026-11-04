package uy.utec.pa.dao;

import uy.utec.pa.modelo.*;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class PostulacionDAOImpl implements PostulacionDAO {

    @Override
    public int crearPostulacion(Postulacion postulacion) {
        String sql = """
        INSERT INTO proyecto.postulaciones (
            nombre, can_mes, linea_investigacion, fecha_solicitud, 
            observaciones_ces, grupo_investigacion,otro_g_investigacion, orden_final_ces, estado, 
            id_departamento, id_postulante, id_sede, id_convocatoria
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)
        """;

        int idGenerado = 0;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, postulacion.getNombre_postulacion());
            sentencia.setInt(2, postulacion.getCantidad_meses());
            sentencia.setString(3, postulacion.getLinea_Investigacion());
            sentencia.setDate(4, java.sql.Date.valueOf(postulacion.getFecha_solicitud()));
            sentencia.setString(5, postulacion.getObservacion_CES());
            sentencia.setString(6, postulacion.getGrupoInvestigacion());
            sentencia.setString(7, postulacion.getOtroGrupoInvestigacion());
            sentencia.setString(8, postulacion.getOrden_final());
            sentencia.setString(9, postulacion.getEstado());
            sentencia.setInt(10, postulacion.getDepartamentoAcademico().getId_DepartamentoAcademico());
            sentencia.setInt(11, postulacion.getPostulante().getId_Postulante());
            sentencia.setInt(12, postulacion.getSede().getId_sede());
            sentencia.setInt(13, postulacion.getConvocatoria().getIdConvocatoria());
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
    SELECT p.id_postulacion,
           p.nombre,
           p.can_mes,
           p.linea_investigacion,
           p.fecha_solicitud,
           p.observaciones_ces,
           p.grupo_investigacion,
           p.otro_g_investigacion,
           p.orden_final_ces,
           p.estado,
           p.id_departamento,
           d.nombre AS nombre_departamento,
           p.id_postulante,
           p.id_sede,
           s.nom_sede AS nombre_sede
    FROM proyecto.postulaciones p
    INNER JOIN proyecto.depa_academicos d
        ON p.id_departamento = d.id_departamento
    INNER JOIN proyecto.sedes s
        ON p.id_sede = s.id_sede
    """;

        String sql2 = "SELECT * FROM proyecto.postulantes WHERE id_postulante=?";

        String sql3 = "SELECT * FROM proyecto.post_celulares WHERE id_postulante=?";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Postulacion postulacion = new Postulacion();

                postulacion.setId_postulacion(resultado.getInt("id_postulacion"));
                postulacion.setNombre_postulacion(resultado.getString("nombre"));
                postulacion.setCantidad_meses(resultado.getInt("can_mes"));
                postulacion.setLinea_Investigacion(resultado.getString("linea_investigacion"));

                if (resultado.getDate("fecha_solicitud") != null) {
                    postulacion.setFecha_solicitud(resultado.getDate("fecha_solicitud").toLocalDate());
                }

                postulacion.setObservacion_CES(resultado.getString("observaciones_ces"));
                postulacion.setGrupoInvestigacion(resultado.getString("grupo_investigacion"));
                postulacion.setOtroGrupoInvestigacion(resultado.getString("otro_g_investigacion"));
                postulacion.setOrden_final(resultado.getString("orden_final_ces"));
                postulacion.setEstado(resultado.getString("estado"));

                DepartamentoAcademico departamentoAcademico = new DepartamentoAcademico(resultado.getString("nombre_departamento"),resultado.getInt("id_departamento"));
                postulacion.setDepartamentoAcademico(departamentoAcademico);

                Sede sede = new Sede(resultado.getString("nombre_sede"),resultado.getInt("id_sede"));
                postulacion.setSede(sede);

                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setInt(1,resultado.getInt("id_postulante"));
                ResultSet resultado2 = sentencia2.executeQuery();

                    while (resultado2.next()){

                        int idPostulante = resultado2.getInt("id_postulante");
                        String documento = resultado2.getString("documento");
                        String nombre = resultado2.getString("nombre");
                        String apellido = resultado2.getString("apellido");
                        String correo = resultado2.getString("correo");
                        String nacionalidad = resultado2.getString("nacionalidad");
                        String actLaboral = resultado2.getString("act_laboral");
                        boolean esDocUtec = resultado2.getBoolean("es_doc_utec");
                        String calle = resultado2.getString("calle");
                        int numero = resultado2.getInt("numero");
                        String apartamento = resultado2.getString("apartamento");
                        String bis = resultado2.getString("bis");
                        String locProcedencia = resultado2.getString("loc_procedencia");
                        String depProcedencia = resultado2.getString("dep_procedencia");
                        String paiProcedencia = resultado2.getString("pai_procedencia");


                        LinkedList<String> celulares = new LinkedList<>();
                        try (PreparedStatement sentenciaCel = conexion.prepareStatement(sql3)) {
                            sentenciaCel.setInt(1, idPostulante);
                            try (ResultSet resultadoCel = sentenciaCel.executeQuery()) {
                                while (resultadoCel.next()) {
                                    celulares.add(resultadoCel.getString("celular"));
                                }
                            }
                        }

                        Postulante postulante = new Postulante(documento, paiProcedencia, depProcedencia, locProcedencia, idPostulante, correo, nombre, apellido, esDocUtec, nacionalidad, actLaboral, celulares, calle, apartamento, bis, numero);

                        postulacion.setPostulante(postulante);
                    }

                postulaciones.add(postulacion);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al buscar postulaciones.", e);
        }

        return postulaciones;
    }
/*
    @Override
    public LinkedList<Postulacion> listarPostulacionesAsignadas(int id_usuario) {

        LinkedList<Postulacion> postulaciones = new LinkedList<>();

        String sql = """
        SELECT 
         s.id_postulacion
        FROM proyecto.son_asignadas s
        INNER JOIN proyecto.evaluaciones e 
        ON s.id_evaluacion = e.id_evaluacion
        WHERE e.id_usuario = ?
        """;

        String sqlP = """
        SELECT p.id_postulacion,
                p.nombre,
                p.can_mes,
                p.linea_investigacion,
                p.fecha_solicitud,
                p.observaciones_ces,
                p.grupo_investigacion,
                p.otro_g_investigacion,
                p.orden_final_ces,
                p.estado,
                p.id_departamento,
                d.nombre AS nombre_departamento,
        p.id_postulante,
                p.id_sede,
                s.nom_sede AS nombre_sede
        FROM proyecto.postulaciones p
        INNER JOIN proyecto.depa_academicos d
        ON p.id_departamento = d.id_departamento
        INNER JOIN proyecto.sedes s
        ON p.id_sede = s.id_sede
        WHERE p.id_postulacion = ?
        """;

            String sql2 = "SELECT * FROM proyecto.postulantes WHERE id_postulante=?";

            String sql3 = "SELECT * FROM proyecto.post_celulares WHERE id_postulante=?";

            try {
                Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                 PreparedStatement sentencia = conexion.prepareStatement(sql);
                 sentencia.setInt(1, id_usuario);
                 ResultSet resultado = sentencia.executeQuery();

                while (resultado.next()) {
                    int id_postulacion = resultado.getInt("id_postulacion");

                    PreparedStatement sentencia2 = conexion.prepareStatement(sqlP);
                    sentencia2.setInt(1, id_postulacion);
                    ResultSet resultado2 = sentencia2.executeQuery();
                    while (resultado2.next()) {

                        Postulacion postulacion = new Postulacion();

                        postulacion.setId_postulacion(resultado2.getInt("id_postulacion"));
                        postulacion.setNombre_postulacion(resultado2.getString("nombre"));
                        postulacion.setCantidad_meses(resultado2.getInt("can_mes"));
                        postulacion.setLinea_Investigacion(resultado2.getString("linea_investigacion"));

                        if (resultado2.getDate("fecha_solicitud") != null) {
                            postulacion.setFecha_solicitud(resultado2.getDate("fecha_solicitud").toLocalDate());
                        }

                        postulacion.setObservacion_CES(resultado2.getString("observaciones_ces"));
                        postulacion.setGrupoInvestigacion(resultado2.getString("grupo_investigacion"));
                        postulacion.setOtroGrupoInvestigacion(resultado2.getString("otro_g_investigacion"));
                        postulacion.setOrden_final(resultado2.getString("orden_final_ces"));
                        postulacion.setEstado(resultado2.getString("estado"));

                        DepartamentoAcademico departamentoAcademico = new DepartamentoAcademico(resultado2.getString("nombre_departamento"), resultado2.getInt("id_departamento"));
                        postulacion.setDepartamentoAcademico(departamentoAcademico);

                        Sede sede = new Sede(resultado2.getString("nombre_sede"), resultado2.getInt("id_sede"));
                        postulacion.setSede(sede);

                        PreparedStatement sentencia3 = conexion.prepareStatement(sql2);
                        sentencia3.setInt(1, resultado2.getInt("id_postulante"));
                        ResultSet resultado3 = sentencia3.executeQuery();

                        while (resultado3.next()) {

                            int idPostulante = resultado3.getInt("id_postulante");
                            String documento = resultado3.getString("documento");
                            String nombre = resultado3.getString("nombre");
                            String apellido = resultado3.getString("apellido");
                            String correo = resultado3.getString("correo");
                            String nacionalidad = resultado3.getString("nacionalidad");
                            String actLaboral = resultado3.getString("act_laboral");
                            boolean esDocUtec = resultado3.getBoolean("es_doc_utec");
                            String calle = resultado3.getString("calle");
                            int numero = resultado3.getInt("numero");
                            String apartamento = resultado3.getString("apartamento");
                            String bis = resultado3.getString("bis");
                            String locProcedencia = resultado3.getString("loc_procedencia");
                            String depProcedencia = resultado3.getString("dep_procedencia");
                            String paiProcedencia = resultado3.getString("pai_procedencia");


                            LinkedList<String> celulares = new LinkedList<>();
                            try (PreparedStatement sentenciaCel = conexion.prepareStatement(sql3)) {
                                sentenciaCel.setInt(1, idPostulante);
                                try (ResultSet resultadoCel = sentenciaCel.executeQuery()) {
                                    while (resultadoCel.next()) {
                                        celulares.add(resultadoCel.getString("celular"));
                                    }
                                }
                            }

                            Postulante postulante = new Postulante(documento, paiProcedencia, depProcedencia, locProcedencia, idPostulante, correo, nombre, apellido, esDocUtec, nacionalidad, actLaboral, celulares, calle, apartamento, bis, numero);

                            postulacion.setPostulante(postulante);
                        }

                        postulaciones.add(postulacion);
                    }
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Error al buscar postulaciones.", e);
            }

            return postulaciones;
        }*/

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
        otro_g_investigacion = ?,
        orden_final_ces = ?, 
        id_departamento = ?, 
        id_postulante = ?, 
        id_sede = ?,
        estado = ?
    WHERE id_postulacion = ?
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, postulacion.getNombre_postulacion());
            sentencia.setInt(2, postulacion.getCantidad_meses());
            sentencia.setString(3, postulacion.getLinea_Investigacion());
            sentencia.setDate(4, java.sql.Date.valueOf(postulacion.getFecha_solicitud()));
            sentencia.setString(5, postulacion.getObservacion_CES());
            sentencia.setString(6,postulacion.getGrupoInvestigacion());
            sentencia.setString(7,postulacion.getOtroGrupoInvestigacion());
            sentencia.setString(8, postulacion.getOrden_final());
            sentencia.setInt(9, postulacion.getDepartamentoAcademico().getId_DepartamentoAcademico());
            sentencia.setInt(10, postulacion.getPostulante().getId_Postulante());
            sentencia.setInt(11, postulacion.getSede().getId_sede());
            sentencia.setString(12, postulacion.getEstado());
            sentencia.setInt(13, postulacion.getId_postulacion());


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

    @Override
    public void actualizarEstadoPostulacion(int idPostulacion, String nuevoEstado) {
        String sql = """
    UPDATE proyecto.postulaciones
    SET estado = ?
    WHERE id_postulacion = ?
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nuevoEstado);
            sentencia.setInt(2, idPostulacion);

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas == 0) {
                throw new IllegalStateException("No se encontró la postulación con ID: " + idPostulacion);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al actualizar el estado de la postulación.", e);
        }
    }




    @Override
    public LinkedList<Postulacion> listarPostulacionesAsignadas(int id_usuario) {

        LinkedList<Postulacion> postulaciones = new LinkedList<>();

        // Consulta optimizada utilizando la tabla relacional son_asignadas
        String sql = """
        SELECT DISTINCT p.id_postulacion,
                p.nombre,
                p.can_mes,
                p.linea_investigacion,
                p.fecha_solicitud,
                p.observaciones_ces,
                p.grupo_investigacion,
                p.otro_g_investigacion,
                p.orden_final_ces,
                p.estado,
                p.id_departamento,
                d.nombre AS nombre_departamento,
                p.id_postulante,
                p.id_sede,
                s.nom_sede AS nombre_sede
        FROM proyecto.postulaciones p
        INNER JOIN proyecto.depa_academicos d
            ON p.id_departamento = d.id_departamento
        INNER JOIN proyecto.sedes s
            ON p.id_sede = s.id_sede
        INNER JOIN proyecto.son_asignadas sa
            ON p.id_postulacion = sa.id_postulacion
        WHERE sa.id_usuario = ?
        """;

        String sql2 = "SELECT * FROM proyecto.postulantes WHERE id_postulante=?";
        String sql3 = "SELECT * FROM proyecto.post_celulares WHERE id_postulante=?";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id_usuario);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    Postulacion postulacion = new Postulacion();

                    postulacion.setId_postulacion(resultado.getInt("id_postulacion"));
                    postulacion.setNombre_postulacion(resultado.getString("nombre"));
                    postulacion.setCantidad_meses(resultado.getInt("can_mes"));
                    postulacion.setLinea_Investigacion(resultado.getString("linea_investigacion"));

                    if (resultado.getDate("fecha_solicitud") != null) {
                        postulacion.setFecha_solicitud(resultado.getDate("fecha_solicitud").toLocalDate());
                    }

                    postulacion.setObservacion_CES(resultado.getString("observaciones_ces"));
                    postulacion.setGrupoInvestigacion(resultado.getString("grupo_investigacion"));
                    postulacion.setOtroGrupoInvestigacion(resultado.getString("otro_g_investigacion"));
                    postulacion.setOrden_final(resultado.getString("orden_final_ces"));
                    postulacion.setEstado(resultado.getString("estado"));

                    DepartamentoAcademico departamentoAcademico = new DepartamentoAcademico(resultado.getString("nombre_departamento"), resultado.getInt("id_departamento"));
                    postulacion.setDepartamentoAcademico(departamentoAcademico);

                    Sede sede = new Sede(resultado.getString("nombre_sede"), resultado.getInt("id_sede"));
                    postulacion.setSede(sede);

                    try (PreparedStatement sentencia2 = conexion.prepareStatement(sql2)) {
                        sentencia2.setInt(1, resultado.getInt("id_postulante"));
                        try (ResultSet resultado3 = sentencia2.executeQuery()) {
                            while (resultado3.next()) {
                                int idPostulante = resultado3.getInt("id_postulante");
                                String documento = resultado3.getString("documento");
                                String nombre = resultado3.getString("nombre");
                                String apellido = resultado3.getString("apellido");
                                String correo = resultado3.getString("correo");
                                String nacionalidad = resultado3.getString("nacionalidad");
                                String actLaboral = resultado3.getString("act_laboral");
                                boolean esDocUtec = resultado3.getBoolean("es_doc_utec");
                                String calle = resultado3.getString("calle");
                                int numero = resultado3.getInt("numero");
                                String apartamento = resultado3.getString("apartamento");
                                String bis = resultado3.getString("bis");
                                String locProcedencia = resultado3.getString("loc_procedencia");
                                String depProcedencia = resultado3.getString("dep_procedencia");
                                String paiProcedencia = resultado3.getString("pai_procedencia");

                                LinkedList<String> celulares = new LinkedList<>();
                                try (PreparedStatement sentenciaCel = conexion.prepareStatement(sql3)) {
                                    sentenciaCel.setInt(1, idPostulante);
                                    try (ResultSet resultadoCel = sentenciaCel.executeQuery()) {
                                        while (resultadoCel.next()) {
                                            celulares.add(resultadoCel.getString("celular"));
                                        }
                                    }
                                }

                                Postulante postulante = new Postulante(documento, paiProcedencia, depProcedencia, locProcedencia, idPostulante, correo, nombre, apellido, esDocUtec, nacionalidad, actLaboral, celulares, calle, apartamento, bis, numero);
                                postulacion.setPostulante(postulante);
                            }
                        }
                    }

                    postulaciones.add(postulacion);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al buscar postulaciones asignadas.", e);
        }

        return postulaciones;
    }


}
