package uy.utec.pa.dao;

import uy.utec.pa.modelo.Administrador;
import uy.utec.pa.modelo.Usuario;

import java.util.LinkedList;

public interface UsuarioDAO {
    int buscarUsuarioPorCorreo(String correo);
    void crearUsuarioAdministrador(Administrador administrador);
    LinkedList<Usuario> obtenerTodosLosUsuarios();
    void editarUsuario(Usuario usuario);
    Administrador buscarRestoDeDatosAdministrador(Usuario usuario);
    void editarAdministrador(Administrador administrador);
}
