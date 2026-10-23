package uy.utec.pa.dao;

import uy.utec.pa.modelo.Criterio;
import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CriterioDAOImpl implements CriterioDAO {

    @Override
    public void crearCriterios(LinkedList<Criterio> criterios) {

        String sql = """
            
                INSERT INTO proyecto.criterios
            (nom_criterio, id_secrubrica)
            VALUES (?, ?)
            """;

        Connection conexion = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();

            conexion.setAutoCommit(false);

            try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

                for (Criterio criterio : criterios) {

                    sentencia.setString(
                            1,
                            criterio.getNombre()
                    );

                    sentencia.setInt(
                            2,
                            criterio.getSeccionRubrica().getId_secRubrica()
                    );

                    sentencia.addBatch();
                }

                sentencia.executeBatch();

                conexion.commit();

                System.out.println("Criterios registrados correctamente.");

            } catch (SQLException e) {

                conexion.rollback();

                System.out.println(
                        "Error al registrar los criterios. Se realizó rollback."
                );

                throw e;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al crear criterios: " + e.getMessage()
            );

        } finally {

            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println(
                            "Error al cerrar la conexión: " + e.getMessage()
                    );
                }
            }
        }
    }


    @Override
    public void editarCriterio(Criterio criterio) {
        String sql =
                """
            UPDATE
                proyecto.criterios
                  SET nom_criterio = 
                       id_secrubrica 
                   WHERE id_criterio = ?
            """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, criterio.getNombre());
            sentencia.setInt(2, criterio.getSeccionRubrica().getId_secRubrica());
            sentencia.setInt(3, criterio.getIdCriterio());

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("¡Criterio actualizado correctamente!");
            } else {
                System.out.println("No se encontró ningún criterio con ese ID para editar.");
            }

        } catch (SQLException e) {
            System.out.println("Error al editar el criterio: " + e.getMessage());
            throw new IllegalStateException("Error al edit el criteio.", e) ;
        }
    }

@Override
public  LinkedList<Criterio> listarCriterios(int idSeccion){

        LinkedList<Criterio> criterios = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.criterios WHERE id_secrubrica=?";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,idSeccion);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_criterio = resultado.getInt("id_criterio");
                String nom_criterio = resultado.getString("nom_criterio");
                Criterio criterio = new Criterio(id_criterio,nom_criterio);
                criterios.add(criterio);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la rubrica.", e
            );
        }
        return criterios;
    }







}




