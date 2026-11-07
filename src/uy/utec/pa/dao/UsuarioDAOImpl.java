package uy.utec.pa.dao;

import uy.utec.pa.modelo.Administrador;
import uy.utec.pa.modelo.EvaluadorExterno;
import uy.utec.pa.modelo.Usuario;
import uy.utec.pa.util.ConexionBDSingleton;
import uy.utec.pa.util.HashContrasena;

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
             ci, contrasena, estado, rol)
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
            sentencia.setString(7, HashContrasena.generarHash(administrador.getContrasena()));
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

    public void crearUsuarioEvaluador(EvaluadorExterno evaluador){
        String sql = """
        INSERT INTO proyecto.usuarios
            (correo, nombre, apellido, calle, nacionalidad,
             ci, contrasena, estado, rol)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        String sql2 = """
        INSERT INTO proyecto.eva_externos
            (id_usuario,institucion)
        VALUES (?, ?)
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, evaluador.getCorreo());
            sentencia.setString(2, evaluador.getNombre());
            sentencia.setString(3, evaluador.getApellido());
            sentencia.setString(4, evaluador.getCalle());
            sentencia.setString(5, evaluador.getNacionalidad());
            sentencia.setString(6, evaluador.getCi());
            sentencia.setString(7, HashContrasena.generarHash(evaluador.getContrasena()));
            sentencia.setString(8, evaluador.getEstado());
            sentencia.setString(9, evaluador.getRol());

            sentencia.executeUpdate();

            int id_usuario_generado = buscarUsuarioPorCorreo(evaluador.getCorreo());

            sentencia = conexion.prepareStatement(sql2);
            sentencia.setInt(1, id_usuario_generado);
            sentencia.setString(2, evaluador.getInstitucion());

            sentencia.executeUpdate();

            System.out.println("Evaluador Externo creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear evaluador externo: " + e.getMessage());

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

    public void darDeBajaUsuario (int id_usuario) {

        String sql = """
                UPDATE proyecto.usuarios
                        SET estado = 'inactivo'
                        WHERE
                id_Usuario = ?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, id_usuario);

            sentencia.executeUpdate();

            System.out.println("Usuario dado de baja correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al dar de baja el usuario: " + e.getMessage());

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
    public boolean esContraseñaGenerica(String correo){
        String sql = "SELECT contrasena FROM proyecto.usuarios WHERE correo = ?";
        boolean esGenerica = false;
        try  {
            Connection conexion = ConexionBDSingleton.getInstancia().conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, correo);

            String hashContrasenaGenerica = HashContrasena.generarHash("secu1234");

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    String contraseña =  resultado.getString("contrasena");
                    if(contraseña.equals(hashContrasenaGenerica)){
                        esGenerica = true;
                    }
                }

            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error al buscar el usuario.", e
            );
        }
        return esGenerica;
    }

    @Override
    public void guardarContraseñaNueva(String correo, String nuevaContrasena){
        String sql = """
        UPDATE proyecto.usuarios
        SET contrasena = ?
        WHERE correo = ?
        """;

        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBDSingleton.getInstancia().conectar();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, nuevaContrasena);
            sentencia.setString(2, correo);

            sentencia.executeUpdate();


            System.out.println("Contraseña cambiada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear evaluador externo: " + e.getMessage());

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
    public LinkedList<EvaluadorExterno> listarEvaluadoresExternos() {
        LinkedList<EvaluadorExterno> evaluadores = new LinkedList<>();

        String sql = """
        SELECT u.id_usuario, u.correo, u.nombre, u.apellido, u.calle, u.numero,
               u.nacionalidad, u.ci, u.contrasena, u.estado, u.rol, e.institucion
        FROM proyecto.usuarios u
        INNER JOIN proyecto.eva_externos e ON u.id_usuario = e.id_usuario
    """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                EvaluadorExterno evaluador = new EvaluadorExterno(
                        resultado.getInt("id_usuario"),
                        resultado.getString("ci"),
                        resultado.getString("nacionalidad"),
                        resultado.getString("contrasena"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("calle"),
                        resultado.getInt("numero"),
                        resultado.getString("estado"),
                        resultado.getString("correo"),
                        resultado.getString("rol"),
                        resultado.getString("institucion"));


                        evaluadores.add(evaluador);

            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error al listar evaluadores externos.", e);
        }

        return evaluadores;
    }

    @Override
    public boolean verificarCedulaExistente(String cedula) {
        String sql = "SELECT EXISTS (SELECT 1 FROM proyecto.usuarios WHERE ci = ?)";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean verificarCorreoExistente(String correo) {
        String sql = "SELECT EXISTS (SELECT 1 FROM proyecto.usuarios WHERE LOWER(correo) = LOWER(?))";

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void aceptarAcuerdos(int id_usuario,int id_postulacion){
        String sql = """
        UPDATE proyecto.son_asignadas
        SET fec_ace_acu_confidencialidad = CURRENT_DATE,
            fec_ace_aus_confidencialidad = CURRENT_DATE
        WHERE id_usuario = ? AND id_postulacion = ?
        """;

        try (Connection conexion = ConexionBDSingleton.getInstancia().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id_usuario);
            ps.setInt(2,id_postulacion);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Acuerdos aceptados correctamente.");
            } else {
                System.out.println("No se encontró el evaluador externo.");
            }

        } catch (SQLException e) {
            System.out.println("Error al aceptar los acuerdos: " + e.getMessage());
        }
    }


}