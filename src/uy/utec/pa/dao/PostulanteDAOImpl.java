package uy.utec.pa.dao;

import uy.utec.pa.modelo.Postulante;
import uy.utec.pa.modelo.Sede;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PostulanteDAOImpl implements PostulanteDAO{

    //CREATE
    @Override
    public void crearPostulante(Postulante postulante){
        String sql = """
        INSERT INTO proyecto.postulantes
            (documento,nombre, apellido,correo,nacionalidad,
             act_laboral,es_doc_utec,calle,numero,apartamento,
             bis,loc_procedencia,dep_procedencia,pai_procedencia)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        String sql2 = """
        INSERT INTO proyecto.post_celulares
            (celular,id_postulante)
        VALUES (?,?)
        """;


        Connection conexion = null;
        PreparedStatement sentencia = null;
        PreparedStatement sentencia2 = null;
        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, postulante.getDocumento());
            sentencia.setString(2, postulante.getNombre());
            sentencia.setString(3, postulante.getApellidos());
            sentencia.setString(4, postulante.getCorreo());
            sentencia.setString(5, postulante.getNacionalidad());
            sentencia.setString(6, postulante.getActividad_laboral());
            sentencia.setBoolean(7,  postulante.esDocenteUtec());
            sentencia.setString(8, postulante.getCalle());
            sentencia.setInt(9, postulante.getNumeroCasa());
            sentencia.setString(10, postulante.getApartamento());
            sentencia.setString(11, postulante.getBis());
            sentencia.setString(12, postulante.getLocalidad_procedencia());
            sentencia.setString(13, postulante.getDepartamento_procedencia());
            sentencia.setString(14, postulante.getPais_procedencia());

            sentencia.executeUpdate();

            LinkedList<String> celulares = postulante.getCelulares();
            int idPostulante = buscarPostulantePorDocumento(postulante.getDocumento());

            for (String c: celulares){
                sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setString(1,c);
                sentencia2.setInt(2,idPostulante);
                sentencia2.executeUpdate();
            }

            System.out.println("Se creo correctamente el postulante");



        } catch (SQLException e) {
            System.out.println("Error al crear postulante: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (sentencia2 != null) {
                    sentencia2.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
    }

    @Override
    public LinkedList<Postulante> listarPostulantes(){

        LinkedList<Postulante> postulantes = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.postulantes";

        String sql3 = "SELECT * FROM proyecto.post_celulares WHERE id_postulante=?";

        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {

                int id_postulante = resultado.getInt("id_postulante");
                String documento = resultado.getString("documento");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String correo = resultado.getString("correo");
                String nacionalidad = resultado.getString("nacionalidad");
                String act_laboral = resultado.getString("act_laboral");
                boolean es_doc_utec = resultado.getBoolean("es_doc_utec");
                String calle = resultado.getString("calle");
                int numero = resultado.getInt("numero");
                String apartamento = resultado.getString("apartamento");
                String bis = resultado.getString("bis");
                String loc_procedencia = resultado.getString("loc_procedencia");
                String dep_procedencia = resultado.getString("dep_procedencia");
                String pai_procedencia = resultado.getString("pai_procedencia");

                LinkedList<String> celulares = new LinkedList<>();
                try (PreparedStatement sentenciaCel = conexion.prepareStatement(sql3)) {
                    sentenciaCel.setInt(1, id_postulante);
                    try (ResultSet resultadoCel = sentenciaCel.executeQuery()) {
                        while (resultadoCel.next()) {
                            celulares.add(resultadoCel.getString("celular"));
                        }
                    }
                }

                Postulante postulante = new Postulante(documento, pai_procedencia, dep_procedencia, loc_procedencia, id_postulante, correo, nombre, apellido, es_doc_utec, nacionalidad, act_laboral, celulares, calle, apartamento, bis, numero);
                postulantes.add(postulante);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar postulantes.", e
            );
        }

        return postulantes;
    }


    //READ
    @Override
    public int buscarPostulantePorDocumento(String documento){
        String sql = """
        SELECT id_postulante FROM proyecto.postulantes
        WHERE documento = ?
        """;
        try {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, documento);
            ResultSet resultado = sentencia.executeQuery();


            if (resultado.next()) {
                return resultado.getInt("id_postulante");
            }

            return 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Error .", e);
        }
    }


    //UPDATE
   /*
    @Override
    public void EditarPostulante(Postulante postulante){

        String sql = """
        UPDATE proyecto.postulantes
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
        */


}
