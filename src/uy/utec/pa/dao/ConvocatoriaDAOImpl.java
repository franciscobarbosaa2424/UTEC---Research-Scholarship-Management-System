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
        (descripcion,anio_convocatoria,
         cantidad_becas,tipo_beca,estado,fecha_cierre,
         fecha_apertura,id_rubrica,nombre)
    VALUES (?,?,?,?,?,?,?,?,?)
    """;



        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, convocatoria.getDescripcion());
            sentencia.setInt(2, convocatoria.getAnio());
            sentencia.setInt(3, convocatoria.getCantidadBecas());
            sentencia.setString(4, convocatoria.getTipoBeca());
            sentencia.setString(5, convocatoria.getEstado());
            sentencia.setDate(6, convocatoria.getFecha_cierre());
            sentencia.setDate(7,  convocatoria.getFecha_apertura());
            Rubrica aux = convocatoria.getRubrica();
            sentencia.setInt(8, aux.getId_rubrica());
            sentencia.setString(9, convocatoria.getNombre());

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
        String sql2 = "SELECT nombre FROM proyecto.rubricas WHERE id_rubrica = ?";

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
                int idRubrica = resultado.getInt("id_rubrica");

                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setInt(1, idRubrica);
                ResultSet resultado2 = sentencia2.executeQuery();

                Rubrica rubrica = null;
                while (resultado2.next()) {
                    String nombreRubrica = resultado.getString("nombre");
                    rubrica = new Rubrica(idRubrica,nombreRubrica);
                }

                Convocatoria convocatoria = new Convocatoria(idConvocatoria,nombre, anioConvocatoria,descripcion, cantidadBecas, tipoBeca, estado, fechaApertura, fechaCierre,rubrica);
                convocatorias.add(convocatoria);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la convocatoria.", e
            );
        }
        return convocatorias;
    }

    @Override
    public LinkedList<Convocatoria> listarConvocatoriaNoCompletadas(){
        LinkedList<Convocatoria> convocatorias = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.convocatorias WHERE NOT estado = 'completada'";
        String sql2 = "SELECT nombre FROM proyecto.rubricas WHERE id_rubrica = ?";

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
                int idRubrica = resultado.getInt("id_rubrica");

                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setInt(1, idRubrica);
                ResultSet resultado2 = sentencia2.executeQuery();

                Rubrica rubrica = null;
                while (resultado2.next()) {
                    String nombreRubrica = resultado.getString("nombre");
                    rubrica = new Rubrica(idRubrica,nombreRubrica);
                }

                Convocatoria convocatoria = new Convocatoria(idConvocatoria,nombre, anioConvocatoria,descripcion, cantidadBecas, tipoBeca, estado, fechaApertura, fechaCierre,rubrica);
                convocatorias.add(convocatoria);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar la convocatoria.", e
            );
        }
        return convocatorias;
    }

    @Override
    public int buscarConvocatoriaPorNombre(String nombreConvocatoria){
        String sql = """
        SELECT id_convocatoria FROM proyecto.convocatorias
        WHERE nombre = ?
        """;
        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, nombreConvocatoria);
            ResultSet resultado = sentencia.executeQuery();


            if (resultado.next()) {
                return resultado.getInt("id_convocatoria");
            }

            return 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Error .", e);
        }




    }


    //UPDATE, editar convocatoria
    @Override
    public void editarConvocatoria(Convocatoria convocatoria) {
        String sql = """
        UPDATE proyecto.Convocatorias
        SET descripcion = ?,
            anio_convocatoria = ?,
            cantidad_becas = ?,
            tipo_beca = ?,
            estado = ?,
            fecha_cierre = ?,
            fecha_apertura = ?,
            id_rubrica = ?,
            nombre = ?
        WHERE id_convocatoria = ?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, convocatoria.getDescripcion());
            sentencia.setInt(2, convocatoria.getAnio());
            sentencia.setInt(3, convocatoria.getCantidadBecas());
            sentencia.setString(4, convocatoria.getTipoBeca());
            sentencia.setString(5, convocatoria.getEstado());
            sentencia.setDate(6, convocatoria.getFecha_cierre());
            sentencia.setDate(7, convocatoria.getFecha_apertura());

            Rubrica aux = convocatoria.getRubrica();
            sentencia.setInt(8, aux.getId_rubrica());

            sentencia.setString(9, convocatoria.getNombre());
            int idConvocatoria = buscarConvocatoriaPorNombre(convocatoria.getNombre());
            sentencia.setInt(10, idConvocatoria);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Se editó correctamente la convocatoria");
            } else {
                System.out.println("No se encontró la convocatoria para editar");
            }

        } catch (SQLException e) {
            System.out.println("Error al editar Convocatoria: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public void darDeBajaConvocatoria(int idConvocatoria){
            String sql = """
                UPDATE proyecto.convocatorias
                        SET estado = 'completada'
                        WHERE id_convocatoria = ?
        """;

            Connection conexion = null;
            PreparedStatement sentencia = null;

            try {
                conexion = ConexionBDSingleton.getInstancia().conectar();
                sentencia = conexion.prepareStatement(sql);

                sentencia.setInt(1, idConvocatoria);

                sentencia.executeUpdate();

                System.out.println("Convocatoria dada de baja correctamente.");

            } catch (SQLException e) {
                System.out.println("Error al dar de baja la convocatoria: " + e.getMessage());

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
    public int traerIdRubrica(int id_convocatoria) {
        String sql = "SELECT id_rubrica FROM proyecto.convocatorias WHERE id_convocatoria = ?";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id_convocatoria);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("id_rubrica");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al traer el ID de la rúbrica: " + e.getMessage());
        }

        return 0;
    }




    }



