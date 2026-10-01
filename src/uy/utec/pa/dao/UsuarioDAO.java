package uy.utec.pa.dao;

import uy.utec.pa.modelo.Administrador;
import uy.utec.pa.util.ConexionBDSingleton;
import java.sql.*;

public class UsuarioDAO {

    public String buscarPorCorreo(String correo) throws SQLException {

        String sql = "SELECT * FROM proyecto.usuarios WHERE correo = ?";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, correo);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return "Usuario encontrado: " + resultado.getString("correo");
                }

                return "Usuario no encontrado";
            }
        }
    }
/*
    public void crearUsuarioAdministrador(Administrador administrador) throws SQLException{
        String sql = """
        INSERT INTO proyecto.usuarios(correo,nombre,apellido,calle,nacionalidad
                """
        try{
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
        }
    }*/
}