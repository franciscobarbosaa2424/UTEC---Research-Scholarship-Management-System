package uy.utec.pa.dao;

import uy.utec.pa.modelo.Convocatoria;
import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.util.LinkedList;

public class ConvocatoriaDAOImpl implements ConvocatoriaDAO {

    @Override
    public void crearConvocatoria(Convocatoria convocatoria){
        String sql = """
    INSERT INTO proyecto.Convocatorias
        (id_convocatoria,descripcion,anio_convocatoria,
         cantidad_becas,tipo_beca,estado,fecha_cierre,
         fecha_apertura,id_rubrica,nombre)
    VALUES (?, ?, ?, ?,?,?,?,?,?,?)
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
            Rubrica aux = convocatoria.getRubrica();
            sentencia.setInt(9, aux.getId_rubrica());
            sentencia.setString(10, convocatoria.getNombre());

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

    @Override
    public LinkedList<Convocatoria> listarConvocatorias(){
        LinkedList<Convocatoria> convocatorias = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.convocatorias";


        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int idConvocatoria = resultado.getInt("id_convocatoria");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                int anioConvocatoria = resultado.getInt("anio_convocatoria");
                int cantidadBecas = resultado.getInt("cantidad_becas");
                String tipoBeca = resultado.getString("tipo_beca");
                String estado = resultado.getString("estado");
                java.sql.Date fechaCierre = resultado.getDate("fecha_cierre");
                java.sql.Date fechaApertura = resultado.getDate("fecha_apertura");

                Convocatoria convocatoria = new Convocatoria(idConvocatoria,nombre, anioConvocatoria,descripcion, cantidadBecas, tipoBeca, estado, fechaApertura, fechaCierre);
                convocatorias.add(convocatoria);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la convocatoria.", e
            );
        }
        return convocatorias;
    }





}
