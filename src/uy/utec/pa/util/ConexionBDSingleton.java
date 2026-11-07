package uy.utec.pa.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDSingleton {

    private static ConexionBDSingleton instancia;

    private ConexionBDSingleton() {
    }

    private static final String URL = System.getenv("URL");
    private static final String USUARIO = System.getenv("USUARIO");
    private static final String CONTRASENA = System.getenv("CONTRASENA");

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