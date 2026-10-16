package uy.utec.pa.dao;

import uy.utec.pa.modelo.Convocatoria;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConvocatoriaDAOImpl implements ConvocatoriaDAO {

    @Override
    public void crearConvocatoria(Convocatoria convocatoria){
        String sql = """
    INSERT INTO proyecto.Convocatorias
        (id_convocatoria,descripcion,anio_convocatoria,
         cantidad_becas,tipo_beca,estado,fecha_cierre,
         fecha_apertura,id_rubrica)
    VALUES (?, ?, ?, ?,?,?,?,?,?)
    """;



        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, convocatoria.getIdConvocatoria());
            sentencia.setString(2, convocatoria.getDescripcion());
            sentencia.setInt(3, convocatoria.getAnio());
            sentencia.setInt(4, convocatoria.getCantidadBecas());
            sentencia.setString(5, convocatoria.getTipoBeca());
            sentencia.setString(6, convocatoria.getEstado());
            sentencia.setDate(7, convocatoria.getFecha_cierre());
            sentencia.setDate(8,  convocatoria.getFecha_apertura());
            sentencia.setInt(9, convocatoria.getIdConvocatoria());


            sentencia.executeUpdate();
            System.out.println("Se creo correctamente la convocatoria");

        } catch (SQLException e) {
            System.out.println("Error al crear Convocatoria: " + e.getMessage());

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
