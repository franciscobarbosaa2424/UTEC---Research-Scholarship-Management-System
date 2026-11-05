package uy.utec.pa.servicio;

import uy.utec.pa.modelo.Usuario;
import uy.utec.pa.util.HashContrasena;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutenticacionReal implements ServicioAutenticacion{
    private List<Usuario> usuarios;

    public AutenticacionReal(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean autenticar(String correo, String contrasena) {

        String contrasenaGuardada = null;
        for(Usuario u : usuarios){
            if(u.getCorreo().equalsIgnoreCase(correo) && u.getEstado().equalsIgnoreCase("activo")){
                contrasenaGuardada = u.getContrasena();
            }

        }

        boolean autentica;
       // System.out.println("contraseña: "+contrasena);
      //  System.out.println("constraseña hasheada: "+contrasenaGuardada);
        if (contrasenaGuardada != null && contrasenaGuardada.equals(contrasena)){
            autentica = true;
        }else{
            autentica = false;
        }

        return autentica;
    }

    @Override
    public boolean usuarioInactivo(String correo){
        boolean aux = false;
        for(Usuario u : usuarios){
            if(u.getEstado().equalsIgnoreCase("inactivo")){
                aux=true;
            }
        }
        return aux;
    }
}

