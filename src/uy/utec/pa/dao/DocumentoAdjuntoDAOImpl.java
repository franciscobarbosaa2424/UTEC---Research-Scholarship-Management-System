package uy.utec.pa.dao;

import uy.utec.pa.modelo.DocumentoAdjunto;
import uy.utec.pa.modelo.TipoDocumento;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoAdjuntoDAOImpl implements DocumentoAdjuntoDAO{

    //CREAR
    @Override
    public void crearDocumentoAjunto(DocumentoAdjunto documentoAdjunto){
        String sql = """
        INSERT INTO proyecto.docu_adjuntos(nom_documento, ruta_archivo,id_postulacion,id_tipodocumento)
        VALUES (?,?,?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, documentoAdjunto.getNombre());
            sentencia.setString(2, documentoAdjunto.getRutaArchivo());
            sentencia.setInt(3, documentoAdjunto.getPostulacion().getId_postulacion());
            sentencia.setInt(4,documentoAdjunto.getTipoDocumento().getId_TipoDocumento());

            sentencia.executeUpdate();


            System.out.println("Documento adjunto creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear documento adjunto: " + e.getMessage());

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


    //READ
    @Override
    public int buscarDocumentoAdjuntoPorNombre(String nombre){
        String sql = """
        SELECT id_documento FROM proyecto.docu_adjuntos
        WHERE nom_documento = ?
        """;
        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("id_documento");
            }

            return 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Error al obtener Documento Adjunto.", e);
        }

    }


    //UPDATE
    @Override
    public void editarDocumentoAdjunto(DocumentoAdjunto documentoAdjunto){
            String sql = """
        UPDATE proyecto.docu_adjuntos
        SET nom_documento = ?,
            ruta_archivo = ?
        WHERE id_documento = ?
        """;

            try {
                Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, documentoAdjunto.getNombre());
                sentencia.setString(2, documentoAdjunto.getRutaArchivo());
                sentencia.setInt(3, documentoAdjunto.getId_DocumentoAdjunto());
                sentencia.executeUpdate();

                System.out.println("Documento Adjunto editado correctamente");

            } catch (SQLException e) {
                throw new IllegalStateException("Error al editar el documento adjunto.", e);
            }
        }

    //DELETE
    @Override
    public void eliminarDocumentoAdjunto(int id_documentoAdjunto){
        String sql = """
        DELETE FROM proyecto.docu_adjuntos
        WHERE id_documento = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id_documentoAdjunto);
            sentencia.executeUpdate();
            System.out.println("Documento adjunto eliminado correctamente!");
        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar Documento adjunto.", e);
        }

    }
}
