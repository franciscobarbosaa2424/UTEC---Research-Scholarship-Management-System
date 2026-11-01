package uy.utec.pa.servicio;

public class AutenticacionProxy implements ServicioAutenticacion{
    private final ServicioAutenticacion servicioReal;

    public AutenticacionProxy(ServicioAutenticacion servicioReal) {
        this.servicioReal = servicioReal;
    }


    public boolean autenticar(String correo, String contrasena) {
        if (correo == null || correo.isBlank() || contrasena == null || contrasena.isBlank()) {
            System.out.println("El correo y la contraseña son obligatorios.");
            return false;
        }

        return servicioReal.autenticar(correo, contrasena);
    }

    public boolean usuarioInactivo(String correo){
        return servicioReal.usuarioInactivo(correo);
    }
}
