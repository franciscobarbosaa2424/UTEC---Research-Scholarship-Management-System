package uy.utec.pa.dao;

import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.modelo.SeccionRubrica;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RubricaDAOImpl implements RubricaDAO{
    @Override
    public void crearRubrica(Rubrica rubrica){
        String sql = """
    INSERT INTO proyecto.rubricas
        (nombre)
    VALUES (?)
    """;


        String sql2 = """
    INSERT INTO proyecto.secciones_rubricas
        (nombre,peso,id_rubrica)
    VALUES (?, ?, ?)
    """;


        Connection conexion = null;
        PreparedStatement sentencia = null;
        PreparedStatement sentenciaSeccion = null;
        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, rubrica.getNombre());

            sentencia.executeUpdate();

            boolean transaccionIniciada = false;

                        try {
                            // Inicia la transacción
                            conexion.setAutoCommit(false);
                            transaccionIniciada = true;

                            int idRubrica = buscarRubricaPorId(rubrica.getNombre());

                            for (SeccionRubrica s : rubrica.getSeccionesRubrica()) {
                                sentenciaSeccion = conexion.prepareStatement(sql2);
                                sentenciaSeccion.setString(1, s.getNombre());
                                sentenciaSeccion.setInt(2, s.getPeso());
                                sentenciaSeccion.setInt(3, idRubrica);

                                // Cargar parámetros y ejecutar el INSERT del usuario
                                sentenciaSeccion.executeUpdate();

                            }

                            // Confirma ambos INSERT
                            conexion.commit();
                        }catch (SQLException e) {
                            if (transaccionIniciada) {
                                try {
                                    conexion.rollback();
                                } catch (SQLException errorRollback) {
                                    System.out.println("Error al deshacer los cambios: "
                                            + errorRollback.getMessage());
                                }
                            }

                            System.out.println("Error al crear la rúbrica: " + e.getMessage());
                        }

            System.out.println("Rubrica creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear rubrica: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if(sentenciaSeccion != null){
                    sentenciaSeccion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
    }

    @Override
    public int buscarRubricaPorId(String nombre){
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        String sql = "SELECT id_rubrica FROM proyecto.rubricas WHERE nombre = ?";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, nombre);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("id_rubrica");
                }

                return 0;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la rubrica.", e
            );
        }
    }

}
