package uy.utec.pa.dao;

import uy.utec.pa.modelo.Administrador;
import uy.utec.pa.modelo.Usuario;
import uy.utec.pa.util.ConexionBDSingleton;
import java.sql.*;
import java.util.LinkedList;

public class UsuarioDAOImpl implements UsuarioDAO{
    @Override
    public int buscarUsuarioPorCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }

        String sql = "SELECT id_usuario FROM proyecto.usuarios WHERE correo = ?";

        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, correo);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("id_usuario");
                }

                return 0; // No existe un usuario con ese correo
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar el usuario por correo.", e
            );
        }
    }

    @Override
    public void crearUsuarioAdministrador(Administrador administrador) {

        String sql = """
        INSERT INTO proyecto.usuarios
            (correo, nombre, apellido, calle, nacionalidad,
             ci, contraseña, estado, rol)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;



        String sql2 = """
        INSERT INTO proyecto.administradores
            (id_usuario,adm_permiso,fec_alta,puesto)
        VALUES (?, ?, ?, ?)
        """;



        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, administrador.getCorreo());
            sentencia.setString(2, administrador.getNombre());
            sentencia.setString(3, administrador.getApellido());
            sentencia.setString(4, administrador.getCalle());
            sentencia.setString(5, administrador.getNacionalidad());
            sentencia.setString(6, administrador.getCi());
            sentencia.setString(7, administrador.getContrasena());
            sentencia.setString(8, administrador.getEstado());
            sentencia.setString(9, administrador.getRol());

            sentencia.executeUpdate();

            int id_usuario_generado = buscarUsuarioPorCorreo(administrador.getCorreo());

            sentencia = conexion.prepareStatement(sql2);
            sentencia.setInt(1, id_usuario_generado);
            sentencia.setString(2, administrador.getAdmin_Permiso());
            sentencia.setDate(3, administrador.getFecha_alta());
            sentencia.setString(4, administrador.getPuesto());

            sentencia.executeUpdate();

            System.out.println("Administrador creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear administrador: " + e.getMessage());

        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar sentencia: " + e.getMessage());
            }

        }
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        String sql = """
        UPDATE proyecto.usuarios
        SET ci = ?,
            correo = ?,
            nombre = ?,
            apellido = ?,
            nacionalidad = ?,
            calle = ?,
            numero = ?,
            contrasena = ?,
            estado = ?
        WHERE id_usuario = ?
        """;

        try {
             Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, usuario.getCi());
            sentencia.setString(2, usuario.getCorreo());
            sentencia.setString(3, usuario.getNombre());
            sentencia.setString(4, usuario.getApellido());
            sentencia.setString(5, usuario.getNacionalidad());
            sentencia.setString(6, usuario.getCalle());
            sentencia.setInt(7, usuario.getNumero());
            sentencia.setString(8, usuario.getContrasena());
            sentencia.setString(9, usuario.getEstado());
            sentencia.setInt(10, usuario.getId_usuario());

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas == 0) {
                throw new IllegalStateException("No se encontró el usuario a editar.");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al editar el usuario.", e);
        }
    }

    @Override
    public void editarAdministrador(Administrador administrador) {
        String sql = """
        UPDATE proyecto.administradores
        SET puesto = ?
        WHERE id_usuario = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, administrador.getPuesto());
            sentencia.setInt(2, administrador.getId_usuario());

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas == 0) {
                throw new IllegalStateException(
                        "No se encontró el administrador a editar."
                );
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al editar el puesto del administrador.", e
            );
        }
    }

    @Override
    public LinkedList<Usuario> obtenerTodosLosUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        String sql = "SELECT * FROM proyecto.usuarios";

        try{
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id_usuario");
                String correo = resultado.getString("correo");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String calle = resultado.getString("calle");
                int numero = resultado.getInt("numero");
                String nacionalidad = resultado.getString("nacionalidad");
                String ci = resultado.getString("ci");
                String contrasena = resultado.getString("contrasena");
                String estado = resultado.getString("estado");
                String rol = resultado.getString("rol");

                Usuario usuario = new Usuario(id,ci,nacionalidad,contrasena, nombre, apellido, calle, numero, estado,correo, rol);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al obtener los usuarios.", e);
        }

        return usuarios;
    }

    @Override
    public Administrador buscarRestoDeDatosAdministrador(Usuario usuario) {
        String rol = usuario.getRol();

        if (!"administrador".equalsIgnoreCase(rol)) {
            return null;
        }

        String sql = """
        SELECT * FROM proyecto.administradores
        WHERE id_usuario = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, usuario.getId_usuario());

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    java.sql.Date fec_alta = resultado.getDate("fec_alta");
                    String puesto = resultado.getString("puesto");

                    return new Administrador(
                            usuario.getId_usuario(),
                            usuario.getCi(),
                            usuario.getNacionalidad(),
                            usuario.getContrasena(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getCalle(),
                            usuario.getNumero(),
                            usuario.getEstado(),
                            usuario.getCorreo(),
                            puesto,
                            fec_alta,
                            rol
                    );
                }

                return null;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar los datos del administrador.", e
            );
        }
    }



}