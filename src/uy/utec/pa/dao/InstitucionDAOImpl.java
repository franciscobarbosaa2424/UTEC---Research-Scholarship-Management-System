package uy.utec.pa.dao;

import uy.utec.pa.modelo.Convocatoria;
import uy.utec.pa.modelo.Institucion;
import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
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

        ResultSet resultado = null;


        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();

            //el return generated es para cuando la BDD cree el id serial de forma automatica me lo guarde para despues usarlo
            sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            sentencia.setString(1, institucion.getNombre());

            sentencia.executeUpdate();

            //si no es nulo, pasamos el id q genero la BDD a un aux y se lo encajamos en la institucion para q no le quede id=0
            resultado = sentencia.getGeneratedKeys();
            if (resultado.next()) {
                int idGenerado = resultado.getInt(1);
                institucion.setId_Institucion(idGenerado);
            }


            System.out.println("Institucion creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear institucion: " + e.getMessage());

        } finally{
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
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
