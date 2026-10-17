package uy.utec.pa.dao;

import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.modelo.TipoDocumento;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TipoDocumentoDAOImpl implements TipoDocumentoDAO{
    //CREAR TIPO DOC
    @Override
    public void crearTipoDocumento(TipoDocumento tipoDocumento){
        String sql = """
        INSERT INTO proyecto.tipo_documentos(nom_tipo_doc)
        VALUES (?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, tipoDocumento.getNombre());

            sentencia.executeUpdate();


            System.out.println("Tipo documento creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear Tipo documento: " + e.getMessage());

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
    //EDITAR TIPO DOC
    @Override
    public void editarTipoDocumento(TipoDocumento tipoDocumento) {
        String sql = """
        UPDATE proyecto.tipo_documento
        SET nom_tipo_doc = ?
        WHERE id_tipodocumento = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tipoDocumento.getNombre());
            sentencia.setInt(2, tipoDocumento.getId_TipoDocumento());
            sentencia.executeUpdate();

            System.out.println("Documento editado correctamente");

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar el tipo de documento.", e);
        }
    }

    //ELIMINAR TIPO DOC
    @Override
    public void eliminarTipoDocumento(int id_tipoDocumento){
        String sql = """
        DELETE FROM proyecto.tipo_documento
        WHERE id_tipodocumento = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id_tipoDocumento);
            sentencia.executeUpdate();
            System.out.println("Tipo documento eliminado correctamente!");
        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar Tipo Documento.", e);
        }

    }

    @Override
    public int buscarTipoDocumentoPorNombre(String nombre){
        String sql = """
        SELECT id_tipodocumento FROM proyecto.tipo_documento
        WHERE nom_tipo_doc = ?
        """;
        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();


            if (resultado.next()) {
                return resultado.getInt("id_tipodocumento");
            }

            return 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar Tipo Documento.", e);
        }

    }

}