package uy.utec.pa.servicio;

import uy.utec.pa.modelo.EvaluadorExterno;
import uy.utec.pa.modelo.Usuario;

public class EvaluadorFactory extends UsuarioFactory {
    private final String institucion;

    public EvaluadorFactory(String institucion) {
        this.institucion = institucion;
    }

    @Override
    public EvaluadorExterno crearUsuario(
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
        return new EvaluadorExterno(
                ci, nacionalidad, contrasena, nombre, apellido,
                calle, numero, estado, correo, rol, institucion
        );
    }
}
