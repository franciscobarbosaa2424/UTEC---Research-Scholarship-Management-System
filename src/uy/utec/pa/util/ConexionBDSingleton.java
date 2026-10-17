package uy.utec.pa.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDSingleton {

    private static ConexionBDSingleton instancia;

    private ConexionBDSingleton() {
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/proyecto";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "mandira87";

    public static synchronized ConexionBDSingleton getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBDSingleton();
        }

        return instancia;
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}