package uy.utec.pa.dao;

import uy.utec.pa.modelo.Rubrica;
import uy.utec.pa.modelo.SeccionRubrica;
import uy.utec.pa.modelo.Usuario;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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

                            int idRubrica = buscarRubricaPorNombre(rubrica.getNombre());

                            for (SeccionRubrica s : rubrica.getSeccionesRubrica()) {
                                sentenciaSeccion = conexion.prepareStatement(sql2);
                                sentenciaSeccion.setString(1, s.getNombre());
                                sentenciaSeccion.setDouble(2, s.getPeso());
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
    public int buscarRubricaPorNombre(String nombre){
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

    @Override
    public LinkedList<Rubrica> listarRubricas(){
        LinkedList<Rubrica> rubricas = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.rubricas";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id_rubrica = resultado.getInt("id_rubrica");
                String nombre = resultado.getString("nombre");
                Rubrica rubrica = new Rubrica(id_rubrica,nombre);
                rubricas.add(rubrica);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la rubrica.", e
            );
        }
        return rubricas;
    }

    @Override
    public LinkedList<SeccionRubrica> listarSecciones(int id_rubrica){
        String sql = "SELECT * FROM proyecto.secciones_rubricas WHERE id_rubrica = ?";
        LinkedList<SeccionRubrica> secciones = new LinkedList<>();
        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id_rubrica);
            ResultSet resultado = sentencia.executeQuery();


            while (resultado.next()) {
                int id_secrubrica = resultado.getInt("id_secrubrica");
                String nombreRubrica = resultado.getString("nombre");
                double peso = resultado.getDouble("peso");

                SeccionRubrica seccionRubrica = new SeccionRubrica(nombreRubrica,peso,id_secrubrica);
                secciones.add(seccionRubrica);
            }



        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la rubrica.", e
            );
        }
        return secciones;
    }

    @Override
    public void editarRubrica(Rubrica rubrica){
        String sql = """
        UPDATE proyecto.rubricas
        SET nombre = ?
        WHERE id_rubrica = ?
        """;


        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, rubrica.getNombre());
            sentencia.setInt(2, rubrica.getId_rubrica());
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar la rubrica.", e);
        }
    }

    @Override
    public void editarSeccionRubrica(SeccionRubrica seccionRubrica){

        if(seccionRubrica.getId_secRubrica()> 0){

            String sql = """
        UPDATE proyecto.secciones_rubricas
        SET nombre = ?,
        peso = ?
        WHERE id_secRubrica = ?
        """;

            try {
                Connection conexion = ConexionBDSingleton.getInstancia().conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql);

                sentencia.setString(1, seccionRubrica.getNombre());
                sentencia.setDouble(2,seccionRubrica.getPeso());
                sentencia.setInt(3,seccionRubrica.getId_secRubrica());
                sentencia.executeUpdate();

                System.out.println("Rubrica editada correctemente!");

            } catch (SQLException e) {
                throw new IllegalStateException("Error al editar la rubrica.", e);
            }
        }


    }

    @Override
    public void eliminarRubrica(int id_rubrica){
        String sql2 = """
        DELETE FROM proyecto.secciones_rubricas
        WHERE id_rubrica = ?
        """;

        String sql = """
        DELETE FROM proyecto.rubricas
        WHERE id_rubrica = ?
        """;


        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql2);
            sentencia.setInt(1, id_rubrica);
            sentencia.executeUpdate();


            PreparedStatement sentencia2 = conexion.prepareStatement(sql);
            sentencia2.setInt(1, id_rubrica);
            sentencia2.executeUpdate();
            System.out.println("Se elimino la rubrica correctamente");
        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar la rubrica.", e);
        }
    }
}
