package uy.utec.pa.dao;

import uy.utec.pa.modelo.Carrera;
import uy.utec.pa.modelo.Posgrado;
import uy.utec.pa.modelo.Sede;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;
import java.util.LinkedList;

public class SedeDAOImpl implements SedeDAO{

    @Override
    public int crearSede(Sede sede){

        String sql = """
        INSERT INTO proyecto.sedes(nom_sede)
        VALUES (?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        int idGenerado=0;
        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1,sede.getNombre());
            sentencia.executeUpdate();


            resultado = sentencia.getGeneratedKeys();
            if (resultado.next()) {
                idGenerado = resultado.getInt(1);
            }

            System.out.println("Sede creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear sede: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
        return idGenerado;
    }


    @Override
    public void editarSede(Sede sede){
        String sql = """
        UPDATE proyecto.sedes
        SET nom_sede = ?
        WHERE id_sede = ?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, sede.getNombre());
            sentencia.setInt(2,sede.getId_sede());

            sentencia.executeUpdate();

            System.out.println("Sede editada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al editar sede: " + e.getMessage());

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
    public LinkedList<Sede> listarSede() {

        LinkedList<Sede> sedes = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.sedes";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_sede = resultado.getInt("id_sede");
                String nom_sede = resultado.getString("nom_sede");
                Sede sede = new Sede(nom_sede,id_sede);
                sedes.add(sede);

            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar posgrados.", e
            );
        }
        return sedes;
    }


    @Override
    public void eliminarSede(int idSede){
        String sql = """
        DELETE FROM proyecto.sedes
        WHERE id_sede = ?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1,idSede);

            sentencia.executeUpdate();

            System.out.println("Sede eliminada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al editar sede: " + e.getMessage());

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
