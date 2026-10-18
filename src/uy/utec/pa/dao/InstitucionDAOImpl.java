package uy.utec.pa.dao;

import uy.utec.pa.modelo.Convocatoria;
import uy.utec.pa.modelo.Institucion;
import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class InstitucionDAOImpl implements InstitucionDAO{
    //CREATE
    @Override
    public void crearInstitucion(Institucion institucion){
        String sql = """
        INSERT INTO proyecto.instituciones(nom_institucion)
        VALUES (?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, institucion.getNombre());

            sentencia.executeUpdate();

            System.out.println("Institucion creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear institucion: " + e.getMessage());

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
    public LinkedList<Institucion> listarInstituciones(){
        LinkedList<Institucion> instituciones = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.instituciones";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_institucion = resultado.getInt("id_institucion");
                String nom_institucion = resultado.getString("nom_institucion");
                Institucion institucion = new Institucion(nom_institucion,id_institucion);
                instituciones.add(institucion);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar instituciones.", e
            );
        }
        return instituciones;
    }


    //UPDATE
    @Override
    public void editarInstitucion(Institucion institucion) {
        String sql = """
        UPDATE proyecto.instituciones
        SET nom_institucion = ?
        WHERE id_institucion = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, institucion.getNombre());
            sentencia.setInt(2, institucion.getId_Institucion());
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar la rubrica.", e);
        }
    }



}
