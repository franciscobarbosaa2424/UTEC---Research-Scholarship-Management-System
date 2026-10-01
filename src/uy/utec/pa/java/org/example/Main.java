import uy.utec.pa.dao.UsuarioDAO;
import uy.utec.pa.modelo.*;
import uy.utec.pa.util.ConexionBDSingleton;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


    public void main(String[] args) {
        try (Connection conexion = ConexionBDSingleton.conectar()) {
            System.out.println("¡Conexión a PostgreSQL correcta!");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        try {
            UsuarioDAO dao = new UsuarioDAO();
            System.out.println(dao.buscarPorCorreo("francisco@gmail.com"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        Scanner scanner = new Scanner(System.in);

        System.out.println("=== INICIAR SESIÓN ===");

        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        //VALIDAR




            System.out.println("¡Sesión iniciada correctamente!");
            System.out.println();
            System.out.println();

            //MENU
            MenuBytech();

            scanner.close();
    }


public void MenuBytech(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Bienvenido. ¿Qué desea realizar?");
    System.out.println("1. Crear Usuario");
    System.out.print("Ingrese la opción a realizar: ");
    int numero = scanner.nextInt();

    if(numero==1){
        CrearUsuario();
    }
    scanner.close();
}

public void CrearUsuario(){
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


public void CrearUsuarioAdministrador(){
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

    System.out.println("Estado: ");
    String estado = scanner.nextLine();


    System.out.println("Correo: ");
    String correoAdmin = scanner.nextLine();

    System.out.println("Ingrese Puesto: ");
    String puesto = scanner.nextLine();

    Administrador administrador = new Administrador
            (cedula,nacionalidad,contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, puesto);

    System.out.println("Usuario Administrador creado éxitosamente!");

    System.out.println(administrador.toString());

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

    System.out.println("Estado: ");
    String estado = scanner.nextLine();


    System.out.println("Correo: ");
    String correoAdmin = scanner.nextLine();

    System.out.println("Institucion: ");
    String institucion = scanner.nextLine();


    System.out.println("Usuario Evaluador Externo creado éxitosamente!");

    EvaluadorExterno evaluadorExterno = new EvaluadorExterno
            (cedula,nacionalidad,contrasena,nombre, apellido, calle, numeroPuerta, estado, correoAdmin, institucion);


    System.out.println(evaluadorExterno.toString());

    scanner.close();
}

