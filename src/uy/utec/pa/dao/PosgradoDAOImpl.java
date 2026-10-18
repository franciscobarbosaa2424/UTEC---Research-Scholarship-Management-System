package uy.utec.pa.dao;

import uy.utec.pa.modelo.Institucion;
import uy.utec.pa.modelo.Posgrado;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PosgradoDAOImpl implements  PosgradoDAO{

    //CREATE
    @Override
    public void crearPosgrado(Posgrado posgrado){
        String sql = """
        INSERT INTO proyecto.posgrados(nom_posgrado, id_institucion)
        VALUES (?,?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, posgrado.getNombre());
            sentencia.setInt(2, posgrado.getInstitucion().getId_Institucion());

            sentencia.executeUpdate();

            System.out.println("Posgrado creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear posgrado: " + e.getMessage());

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
    public LinkedList<Posgrado> listarPosgrados() {

        LinkedList<Posgrado> posgrados = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.posgrados";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_posgrado = resultado.getInt("id_posgrado");
                String nom_posgrado = resultado.getString("nom_posgrado");
                Posgrado posgrado = new Posgrado(nom_posgrado, id_posgrado);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar posgrados.", e
            );
        }
        return posgrados;




    }


    //UPDATE
    @Override
    public void editarPosgrado(Posgrado posgrado) {
        String sql = """
        UPDATE proyecto.posgrados
        SET nom_posgrado = ?,   
        WHERE id_posgrado = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, posgrado.getNombre());
            sentencia.setInt(2, posgrado.getId_Posgrado());
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar posgrado.", e);
        }
    }

    //DELETE
    @Override
    public void eliminarPosgrado(int id_posgrado){
        String sql = """
        DELETE FROM proyecto.posgrados
        WHERE id_posgrado = ?
        """;

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id_posgrado);
            sentencia.executeUpdate();

            System.out.println("posgrado eliminado correctamente!");
        } catch (SQLException e) {
            throw new IllegalStateException("Error al eliminar posgrado.", e);
        }

    }

}
