package uy.utec.pa.servicio;

import uy.utec.pa.modelo.Administrador;
import uy.utec.pa.modelo.Usuario;

public class AdministradorFactory extends UsuarioFactory {

    private final String puesto;


    public AdministradorFactory(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public Administrador crearUsuario(
            String ci,
            String nacionalidad,
            String contrasena,
            String nombre,
            String apellido,
            String calle,
            int numero,
            String estado,
            String correo,
            String rol
    ) {
        return new Administrador(
                ci, nacionalidad, contrasena, nombre, apellido,
                calle, numero, estado, correo, puesto, rol
        );
    }
}
