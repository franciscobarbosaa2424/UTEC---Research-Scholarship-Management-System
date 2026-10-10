import uy.utec.pa.dao.UsuarioDAOImpl;
import uy.utec.pa.modelo.*;
import uy.utec.pa.servicio.*;


import java.sql.SQLException;
import java.util.Scanner;


    public void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        //VALIDAR
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        LinkedList<Usuario> usuarios = dao.obtenerTodosLosUsuarios();
        ServicioAutenticacion servicioReal = new AutenticacionReal(usuarios);
        ServicioAutenticacion proxy = new AutenticacionProxy(servicioReal);

        System.out.println("=== INICIAR SESIÓN ===");

        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        boolean accede = proxy.autenticar(correo, contrasena);

        if (accede) {
            System.out.println("Inicio de sesión correcto.");
            //MENU
            MenuBytech();
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }

        System.out.println();
        System.out.println();

        scanner.close();
    }


public void MenuBytech() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Bienvenido. ¿Qué desea realizar?");
    System.out.println("1. Crear Usuario");
    System.out.println("2. Editar Usuario");
    System.out.println("3. Listar Usuarios");
    System.out.println("4. Dar de Baja un Usuario");
    System.out.print("Ingrese la opción a realizar: ");
    int numero = scanner.nextInt();

    if(numero==1){
        CrearUsuario();
    }else if(numero==2) {
        System.out.println("Ingrese el ID del usuario a editar de la siguiente lista:");
        LinkedList<Usuario> usuarios = listarUsuarios();
        int id = scanner.nextInt();
        editarUsuario(usuarios,id);
        //llamar a ese metodo aca
    }else if(numero==3){
        listarUsuarios();
    }else if(numero==4){
        darDeBajaUsuario();
    }

    scanner.close();
}


public void CrearUsuario() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("CREACION DE USUARIO:");
    System.out.println("Seleccione modalidad de Usuario: ");
    System.out.println("1.Administrador");
    System.out.println("2.Evaluador Externo");

    int numero = scanner.nextInt();

    if (numero == 1){
        CrearUsuarioAdministrador();
    }//x lista
        if (numero == 2){
            CrearUsuarioEvaluadorExterno();
        }//x lista
          else {}//por defecto

    scanner.close();
}

public void editarUsuario(LinkedList<Usuario> usuarios,int id){
    Scanner scanner = new Scanner(System.in);

    Usuario auxiliar = null;
    for(Usuario u: usuarios){
      if(u.getId_usuario() == id){
          auxiliar = u;
          break;
      }
    }

    System.out.println("Ingrese el nro del campo a editar:");
    System.out.println("1. Cédula: "+auxiliar.getCi());
    System.out.println("2. Correo: "+auxiliar.getCorreo());
    System.out.println("3. Nombre: "+auxiliar.getNombre());
    System.out.println("4. Apellido "+auxiliar.getApellido());
    System.out.println("5. Nacionalidad: "+auxiliar.getNacionalidad());
    System.out.println("6. Calle: "+auxiliar.getCalle());
    System.out.println("7. Numero: "+String.valueOf(auxiliar.getNumero()));
    System.out.println("8. Contraseña: "+auxiliar.getContrasena());
    System.out.println("9. Estado: "+auxiliar.getEstado());

    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    Administrador administrador = null;
    if(auxiliar.getRol().equalsIgnoreCase("administrador")){
        administrador = dao.buscarRestoDeDatosAdministrador(auxiliar);

        System.out.println("10. Puesto: "+administrador.getPuesto());
    }


    int opcionMenuEdicion = scanner.nextInt();

    scanner.nextLine();

    switch (opcionMenuEdicion) {
        case 1:
            System.out.println("Ingrese la nueva cédula:");
            auxiliar.setCi(scanner.nextLine());
            break;

        case 2:
            System.out.println("Ingrese el nuevo correo:");
            auxiliar.setCorreo(scanner.nextLine());
            break;

        case 3:
            System.out.println("Ingrese el nuevo nombre:");
            auxiliar.setNombre(scanner.nextLine());
            break;

        case 4:
            System.out.println("Ingrese el nuevo apellido:");
            auxiliar.setApellido(scanner.nextLine());
            break;

        case 5:
            System.out.println("Ingrese la nueva nacionalidad:");
            auxiliar.setNacionalidad(scanner.nextLine());
            break;

        case 6:
            System.out.println("Ingrese la nueva calle:");
            auxiliar.setCalle(scanner.nextLine());
            break;

        case 7:
            System.out.println("Ingrese el nuevo número:");
            auxiliar.setNumero(scanner.nextInt());
            scanner.nextLine();
            break;

        case 8:
            System.out.println("Ingrese la nueva contraseña:");
            auxiliar.setContrasena(scanner.nextLine());
            break;

        case 9:
            System.out.println("Ingrese el estado (1.Activo o 2.Inactivo):");
            int opcionEstado = scanner.nextInt();
            scanner.nextLine();

            while (opcionEstado != 1 && opcionEstado != 2) {
                System.out.println("Opción inválida. Ingrese 1 o 2:");
                opcionEstado = scanner.nextInt();
                scanner.nextLine();
            }

            auxiliar.setEstado(opcionEstado == 1 ? "Activo" : "Inactivo");
            break;
        case 10:
            if(auxiliar.getRol().equalsIgnoreCase("administrador") && administrador != null){
                System.out.println("Digite nuevo puesto: ");
                administrador.setPuesto(scanner.nextLine());
            }
            break;
        default:
            System.out.println("Opción inválida.");
            break;
    }
    System.out.println("Nuevos datos del usuario: ");
    System.out.println(auxiliar.toString());

    dao.editarUsuario(auxiliar);
    if(auxiliar.getRol().equalsIgnoreCase("administrador") && administrador != null){
        dao.editarAdministrador(administrador);
    }

}

public LinkedList<Usuario> listarUsuarios(){
    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    LinkedList<Usuario> usuarios = dao.obtenerTodosLosUsuarios();
    System.out.println("Usuarios existentes: ");
    for(Usuario u: usuarios){
        System.out.println(u.toString());
    }
    return usuarios;
}


public void CrearUsuarioAdministrador() throws SQLException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Crear usuario ===");

    System.out.println("Cédula: ");
    String cedula = scanner.nextLine();

    System.out.println("Nacionalidad: ");
    String nacionalidad = scanner.nextLine();

    System.out.println("Contraseña: ");
    String contrasena = scanner.nextLine();

    System.out.println("Nombre: ");
    String nombre = scanner.nextLine();


    System.out.println("Apellido: ");
    String apellido = scanner.nextLine();


    System.out.println("Calle: ");
    String calle = scanner.nextLine();


    System.out.println("Número de puerta: ");
    int numeroPuerta = scanner.nextInt();
    scanner.nextLine();


// El formulario continúa acá, solo después de elegir 1 o 2.

    String estado;

    while (true) {
        System.out.println("Estado (1.Activo o 2.Inactivo): ");
        int seleccionEstado = scanner.nextInt();
        scanner.nextLine();

        if (seleccionEstado == 1) {
            estado = "activo";
            break;
        } else if (seleccionEstado == 2) {
            estado = "inactivo";
            break;
        } else {
            System.out.println("Opción inválida, vuelva a seleccionar.");
        }
    }
//
// El formulario continúa acá, solo después de elegir 1 o 2.

    System.out.println("Correo: ");
    String correoAdmin = scanner.nextLine();

    System.out.println("Ingrese Puesto: ");
    String puesto = scanner.nextLine();

    String rol ="administrador";

    AdministradorFactory creadorAdmin = new AdministradorFactory(puesto);
    Administrador usuarioAdmin = creadorAdmin.crearUsuario(
            cedula,
            nacionalidad,
            contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, rol);

    //Administrador administrador = new Administrador
    //        (cedula,nacionalidad,contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, puesto, rol);

    System.out.println("Usuario Administrador creado éxitosamente!");

    System.out.println(usuarioAdmin.toString());

    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    dao.crearUsuarioAdministrador(usuarioAdmin);

    scanner.close();
}

public void CrearUsuarioEvaluadorExterno(){

    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Crear usuario ===");

    System.out.println("Cédula: ");
    String cedula = scanner.nextLine();

    System.out.println("Nacionalidad: ");
    String nacionalidad = scanner.nextLine();

    System.out.println("Contraseña: ");
    String contrasena = scanner.nextLine();

    System.out.println("Nombre: ");
    String nombre = scanner.nextLine();


    System.out.println("Apellido: ");
    String apellido = scanner.nextLine();


    System.out.println("Calle: ");
    String calle = scanner.nextLine();


    System.out.println("Número de puerta: ");
    int numeroPuerta = scanner.nextInt();
    scanner.nextLine();


// El formulario continúa acá, solo después de elegir 1 o 2.

    String estado;

    while (true) {
        System.out.println("Estado (1.Activo o 2.Inactivo): ");
        int seleccionEstado = scanner.nextInt();
        scanner.nextLine();

        if (seleccionEstado == 1) {
            estado = "activo";
            break;
        } else if (seleccionEstado == 2) {
            estado = "inactivo";
            break;
        } else {
            System.out.println("Opción inválida, vuelva a seleccionar.");
        }
    }

// El formulario continúa acá, solo después de elegir 1 o 2.

    System.out.println("Correo: ");
    String correoAdmin = scanner.nextLine();

    System.out.println("Institucion: ");
    String institucion = scanner.nextLine();

    String rol = "evaluador";


    EvaluadorFactory creadorEvaluador = new EvaluadorFactory(institucion);
    EvaluadorExterno usuarioEvaluador = creadorEvaluador.crearUsuario(
            cedula,
            nacionalidad,
            contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, rol);

    //Administrador administrador = new Administrador
    //        (cedula,nacionalidad,contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, puesto, rol);

    System.out.println("Usuario Evaluador Externo creado éxitosamente!");

    System.out.println(usuarioEvaluador.toString());

    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    dao.crearUsuarioEvaluador(usuarioEvaluador);


    scanner.close();
}

public void darDeBajaUsuario(){
    Scanner scanner = new Scanner(System.in);
    listarUsuarios();
    int id = scanner.nextInt();
    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    dao.darDeBajaUsuario(id);
}

