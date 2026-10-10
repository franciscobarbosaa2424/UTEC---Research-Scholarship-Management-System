package uy.utec.pa.servicio;

public interface ServicioAutenticacion {
    boolean autenticar(String correo, String contrasena);
}
