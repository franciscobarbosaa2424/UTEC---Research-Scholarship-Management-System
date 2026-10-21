package uy.utec.pa.dao;

import uy.utec.pa.modelo.Evaluacion;
import uy.utec.pa.util.ConexionBDSingleton;

import java.sql.*;

public class EvaluacionDAOImpl implements EvaluacionDAO {

     //   @Override
   // public void crearEvaluacion(Evaluacion evaluacion){
           /*
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


        }*/



}
