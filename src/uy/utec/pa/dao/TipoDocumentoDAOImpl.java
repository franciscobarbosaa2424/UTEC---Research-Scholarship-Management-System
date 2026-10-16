package uy.utec.pa.dao;

import uy.utec.pa.modelo.TipoDocumento;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TipoDocumentoDAOImpl implements TipoDocumentoDAO{
    @Override
    public void crearTipoDocumento(TipoDocumento tipoDocumento){
    /*    String sql = """
        INSERT INTO proyecto.tipo_documentos
            (correo)
        VALUES (?)
        """;

        String sql2 = """
        INSERT INTO proyecto.eva_externos
            (id_usuario,institucion)
        VALUES (?, ?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, evaluador.getCorreo());
            sentencia.setString(2, evaluador.getNombre());
            sentencia.setString(3, evaluador.getApellido());
            sentencia.setString(4, evaluador.getCalle());
            sentencia.setString(5, evaluador.getNacionalidad());
            sentencia.setString(6, evaluador.getCi());
            sentencia.setString(7, evaluador.getContrasena());
            sentencia.setString(8, evaluador.getEstado());
            sentencia.setString(9, evaluador.getRol());

            sentencia.executeUpdate();

            int id_usuario_generado = buscarUsuarioPorCorreo(evaluador.getCorreo());

            sentencia = conexion.prepareStatement(sql2);
            sentencia.setInt(1, id_usuario_generado);
            sentencia.setString(2, evaluador.getInstitucion());

            sentencia.executeUpdate();

            System.out.println("Evaluador Externo creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear evaluador externo: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }*/
    }


}
