package uy.utec.pa.servicio;

import uy.utec.pa.modelo.Usuario;

public abstract class UsuarioFactory{

    public abstract Usuario crearUsuario(
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
    );


}


