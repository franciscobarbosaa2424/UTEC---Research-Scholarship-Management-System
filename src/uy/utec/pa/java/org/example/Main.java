import uy.utec.pa.dao.*;
import uy.utec.pa.modelo.*;
import uy.utec.pa.servicio.*;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
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
            String rol = "";
            for(Usuario u: usuarios){
                if(u.getCorreo().equalsIgnoreCase(correo)){
                    rol=u.getRol();
                }
            }
            if(rol.equalsIgnoreCase("administrador")){
                System.out.println("Bienvenido Administrador");
                MenuPrincipalAdmin();

            }else { if(rol.equalsIgnoreCase("evaluador")){
                //aca iria el menu cuando el acceso es correcto pero no es administrador
            }}

        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }

        System.out.println();
        System.out.println();

        scanner.close();
    }

    public void MenuPrincipalAdmin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido. ¿Qué desea realizar?");
        System.out.println("1. Menú de Usuarios");
        System.out.println("2. Gestión de Convocatorias");
        System.out.println("3. Gestión de Rúbricas");
        System.out.println("4. ");
        System.out.print("Ingrese la opción a realizar: ");

        int numero = scanner.nextInt();


        if(numero==1){
            MenuUsuario();
        }else if(numero==2){
            GestionConvocatorias();
        }else if(numero==3){
            MenuRubricas();
        }


        scanner.close();

    }

    public void MenuRubricas() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestión de Rúbricas:");
        System.out.println("1. Crear Rúbrica");
        System.out.println("2. Editar Rúbrica");
        System.out.println("3. Listar Rúbricas");
        System.out.println("4. Dar de Baja una Rúbrica");
        System.out.print("Ingrese la opción a realizar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();


        if(numero==1){
            //Crear Rubrica
            System.out.println("Ingrese el nombre de la rubrica:");
            String nombreRubrica = scanner.nextLine();



            boolean valido = true;
            System.out.println("Secciones de la Rúbrica:");
            System.out.println("(Ingrese 0 en nombre para dejar de agregar secciones)");


            LinkedList<SeccionRubrica> seccionesRubrica = new LinkedList<>();

            while(valido==true){
                System.out.println("Ingrese el nombre de la seccion:");
                String nombreSeccion = scanner.nextLine();

                if(nombreSeccion.equalsIgnoreCase("0")){
                    valido=false;
                    break;
                }

                System.out.println("Ingrese el peso de la seccion:");
                double pesoSeccion = scanner.nextDouble();
                scanner.nextLine();

                System.out.println();

                if(pesoSeccion>0 && nombreSeccion!=null && nombreSeccion!=""){
                    SeccionRubrica seccion = new SeccionRubrica(nombreSeccion,pesoSeccion);
                    seccionesRubrica.add(seccion);

                }else{
                    System.out.println("El peso debe ser mayor a 0 y el nombre no debe estar vacio.");
                }

            }

            Rubrica rubrica = new Rubrica(nombreRubrica,seccionesRubrica);
            System.out.println("Rubrica creada con exito!:");
            System.out.println(rubrica.toString());

            RubricaDAOImpl dao = new RubricaDAOImpl();
            dao.crearRubrica(rubrica);

        }else if(numero==2){
                RubricaDAOImpl dao = new RubricaDAOImpl();
                LinkedList<Rubrica> rubricas = dao.listarRubricas();

                //listar rubricas
                for(Rubrica r: rubricas){
                    System.out.println(r.toString());
                }

                Rubrica rubricaAux = null;

                System.out.println("Ingrese el id de la rubrica a editar:");
                int id_rubrica = scanner.nextInt();
                scanner.nextLine();

                for(Rubrica r: rubricas){
                    if(r.getId_rubrica()==id_rubrica){
                        rubricaAux = r;
                        break;
                    }
                }
                boolean auxWhile1 = true;
                boolean auxWhile2 = true;

                //PRIMER WHILE PARA EL PRIMER MENU
                while (auxWhile1==true) {
                        System.out.println("Seleccione la opción que desea editar:");
                        System.out.println("1. Nombre: " + rubricaAux.getNombre());
                        System.out.println("2. Secciones de la rubrica");
                        int opcionEditar = scanner.nextInt();
                        scanner.nextLine();
                        if (opcionEditar == 1) {
                                System.out.println("Ingrese nuevo nombre:");
                                String nombreNuevo = scanner.nextLine();
                                rubricaAux.setNombre(nombreNuevo);
                                dao.editarRubrica(rubricaAux);
                                auxWhile1=false;
                        } else if (opcionEditar == 2) {
                                LinkedList<SeccionRubrica> secciones = dao.listarSecciones(id_rubrica);

                                for (SeccionRubrica s : secciones){
                                    System.out.println(s.toString());
                                }
                                System.out.println("Ingrese el id de seccion a editar:");
                                int opcionSeccion = scanner.nextInt();
                                scanner.nextLine();

                                SeccionRubrica auxiliarSeccion = null;
                                for(SeccionRubrica s: secciones){
                                    if(s.getId_secRubrica() == opcionSeccion){
                                        auxiliarSeccion = s;
                                        break;
                                    }
                                }
                                        //SEGUNDO WHILE PARA EL SEGUNDO MENU
                                        while (auxWhile2==true) {
                                            System.out.println("Ingrese la opción que desea editar");
                                            System.out.println("1. Nombre: " + auxiliarSeccion.getNombre());
                                            System.out.println("2. Peso: " + auxiliarSeccion.getPeso());
                                            int opcionEditarSeccion = scanner.nextInt();
                                            scanner.nextLine();
                                            if (opcionEditarSeccion == 1) {
                                                System.out.println("Ingrese nuevo nombre:");
                                                String nuevoNombre = scanner.nextLine();
                                                auxiliarSeccion.setNombre(nuevoNombre);
                                                auxWhile2=false;
                                            } else if (opcionEditarSeccion == 2) {
                                                System.out.println("Ingrese nuevo peso:");
                                                double nuevoPeso = scanner.nextDouble();
                                                scanner.nextLine();
                                                auxiliarSeccion.setPeso(nuevoPeso);
                                                auxWhile2=false;
                                            }
                                        }

                                dao.editarSeccionRubrica(auxiliarSeccion);

                                auxWhile1=false;
                        } else {
                                System.out.println("Opcion invalida, vuelva a seleccionar");
                        }
                }



        }else if(numero==3){
            // listar rubricas
            RubricaDAOImpl dao = new RubricaDAOImpl();

            for(Rubrica r: dao.listarRubricas()){
                System.out.println("Rubrica:" + r.getId_rubrica() + "-" + r.getNombre());

                if(r.getSeccionesRubrica() != null){
                    for(SeccionRubrica s: r.getSeccionesRubrica()){
                        System.out.println("Seccion:" + s.getNombre() + "-" + s.getPeso());
                    }
                }
            }
        }
        else if(numero==4){
            //listar rubricas
            RubricaDAOImpl dao = new RubricaDAOImpl();

            for(Rubrica r: dao.listarRubricas()){
                System.out.println("Rubrica:" + r.getId_rubrica() + "-" + r.getNombre());

                if(r.getSeccionesRubrica() != null){
                    for(SeccionRubrica s: r.getSeccionesRubrica()){
                        System.out.println("Seccion:" + s.getNombre() + "-" + s.getPeso());
                    }
                }
            }

            // dar de baja rubrica
            System.out.println("Ingrese el ID de la Rubrica a eliminar:");
            int idRubrica = scanner.nextInt();
            scanner.nextLine();
            dao.eliminarRubrica(idRubrica);
        }

        scanner.close();
    }

public void MenuUsuario() throws SQLException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Bienvenido. ¿Qué desea realizar?");
    System.out.println("1. Crear Usuario");
    System.out.println("2. Editar Usuario");
    System.out.println("3. Listar Usuarios");
    System.out.println("4. Dar de Baja un Usuario");
    System.out.print("Ingrese la opción a realizar: ");
    int numero = scanner.nextInt();
    scanner.nextLine();

    if(numero==1){
        CrearUsuario();
    }else if(numero==2) {
        System.out.println("Ingrese el ID del usuario a editar de la siguiente lista:");
        LinkedList<Usuario> usuarios = listarUsuarios();
        int id = scanner.nextInt();
        scanner.nextLine();
        editarUsuario(usuarios,id);
        //llamar a ese metodo aca
    }else if(numero==3){
        listarUsuarios();
    }else if(numero==4){
        darDeBajaUsuario();
    }

    scanner.close();
}

public void GestionConvocatorias() throws SQLException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Gestión de Convocatorias:");
    System.out.println("1. Crear Convocatoria");
    System.out.println("2. Editar Convocatoria");
    System.out.println("3. Listar Convocatorias");
    System.out.println("4. Dar de Baja una Convocatoria");
    System.out.print("Ingrese la opción a realizar: ");
    int numero = scanner.nextInt();
    scanner.nextLine();

    if(numero==1){
        //Crear convocatorias

            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el año de la convocatoria: ");
            int anio = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese la descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Ingrese la cantidad de becas: ");
            int cantidadBecas = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese el tipo de beca: ");
            String tipoBeca = scanner.nextLine();

            System.out.print("Ingrese el estado: ");
            String estado = scanner.nextLine();

            System.out.print("Ingrese fecha de apertura (YYYY-MM-DD): ");
            java.sql.Date fechaApertura = java.sql.Date.valueOf(scanner.nextLine());

            System.out.print("Ingrese fecha de cierre (YYYY-MM-DD): ");
            java.sql.Date fechaCierre = java.sql.Date.valueOf(scanner.nextLine());

            RubricaDAOImpl dao = new RubricaDAOImpl();
            LinkedList<Rubrica> rubricas = dao.listarRubricas();

            System.out.print("Ingrese el id de rubrica a implementar: ");

            Rubrica rubricaAuxiliar = null;

            for(Rubrica r: rubricas){
                System.out.println(r.toString());
            }
            int idRubrica = scanner.nextInt();
            scanner.nextLine();

            for(Rubrica r: rubricas){
                if(r.getId_rubrica()==idRubrica){
                    rubricaAuxiliar = r;
                    break;
                }
            }


            LinkedList<TipoDocumento> tipoDocumentos = new LinkedList<>();
            TipoDocumentoDAOImpl daoTipoDocumento = new TipoDocumentoDAOImpl();
            boolean valido = true;
                while(valido==true){
                    System.out.print("Ingrese Nombre de Tipo Documento (Presione 0 para salir): ");
                    String nombreTipoDocumento = scanner.nextLine();

                    if(nombreTipoDocumento.equalsIgnoreCase("0")){
                        valido=false;
                        break;
                    }

                    TipoDocumento tipoDocumento = new TipoDocumento(nombreTipoDocumento);
                    tipoDocumentos.add(tipoDocumento);


                    daoTipoDocumento.crearTipoDocumento(tipoDocumento);

                }

            Convocatoria convocatoria = new Convocatoria(nombre, anio, descripcion, cantidadBecas,  tipoBeca, estado,  fechaApertura, fechaCierre, rubricaAuxiliar, tipoDocumentos);

            ConvocatoriaDAOImpl daoConvocatoria = new ConvocatoriaDAOImpl();
            daoConvocatoria.crearConvocatoria(convocatoria);

            int idConvocatoria = daoConvocatoria.buscarConvocatoriaPorNombre(convocatoria.getNombre());

            for(TipoDocumento t: tipoDocumentos){
                int idTipoDocumento = daoTipoDocumento.buscarTipoDocumentoPorNombre(t.getNombre());
                daoTipoDocumento.asignarTipoDocumentoConvocatoria(idConvocatoria,idTipoDocumento);
            }




    }else if(numero==2){
        //listar convocatorias
        ConvocatoriaDAOImpl dao = new ConvocatoriaDAOImpl();
        List<Convocatoria> listaConvocatorias = dao.listarConvocatorias();
        System.out.println("Ingrese el id de convocatoria a editar: ");
        for(Convocatoria c: listaConvocatorias){
            System.out.println(c.toString());
        }
        int idConvocatoria = scanner.nextInt();
        scanner.nextLine();

        //elegir convocatorias y poder editarla
                Convocatoria convocatoriaAux = null;

                for(Convocatoria c: listaConvocatorias){
                    if(c.getIdConvocatoria()==idConvocatoria){
                        convocatoriaAux = c;
                        break;
                    }
                }
                boolean auxWhile1 = true;


                //PRIMER WHILE PARA EL PRIMER MENU
                while (auxWhile1==true) {
                    System.out.println("Seleccione la opción que desea editar:");
                    System.out.println("1. Nombre: " + convocatoriaAux.getNombre());
                    System.out.println("2. Descripción: "+convocatoriaAux.getDescripcion());
                    System.out.println("3. Año: "+convocatoriaAux.getAnio());
                    System.out.println("4. Cantidad de becas: "+convocatoriaAux.getIdConvocatoria());
                    System.out.println("5. Tipo de beca: "+convocatoriaAux.getTipoBeca());
                    System.out.println("6. Estado: "+convocatoriaAux.getEstado());
                    System.out.println("7. Fecha de Apertura: "+convocatoriaAux.getFecha_apertura());
                    System.out.println("8. Fecha de Cierre: "+convocatoriaAux.getFecha_cierre());
                    Rubrica rubricaConvocatoria = convocatoriaAux.getRubrica();
                    System.out.println("9. Rubrica: "+rubricaConvocatoria.toString());
                    System.out.println("10. Editar Tipos Documentos");

                    int opcionEditar = scanner.nextInt();
                    scanner.nextLine();

                        if (opcionEditar == 1) {
                            System.out.println("Ingrese nuevo nombre:");
                            String nombreNuevo = scanner.nextLine();
                            convocatoriaAux.setNombre(nombreNuevo);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 2) {
                            System.out.println("Ingrese nueva descripción:");
                            String descripcionNueva = scanner.nextLine();
                            convocatoriaAux.setDescripcion(descripcionNueva);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 3) {
                            System.out.println("Ingrese nuevo año:");
                            int anioNuevo = scanner.nextInt();
                            scanner.nextLine();
                            convocatoriaAux.setAnio(anioNuevo);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 4) {
                            System.out.println("Ingrese nueva cantidad de becas:");
                            int cantidadNueva = scanner.nextInt();
                            scanner.nextLine();
                            convocatoriaAux.setCantidadBecas(cantidadNueva); // (Asumiendo que tienes este setter en Convocatoria)
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 5) {
                            System.out.println("Ingrese nuevo tipo de beca:");
                            String tipoBecaNuevo = scanner.nextLine();
                            convocatoriaAux.setTipoBeca(tipoBecaNuevo);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 6) {
                            String estadoNuevo;
                            while (true) {
                                System.out.println("Seleccione nuevo estado (1. Activo o 2. Inactivo): ");
                                int seleccionEstado = scanner.nextInt();
                                scanner.nextLine();

                                if (seleccionEstado == 1) {
                                    estadoNuevo = "activo";
                                    break;
                                } else if (seleccionEstado == 2) {
                                    estadoNuevo = "inactivo";
                                    break;
                                } else {
                                    System.out.println("Opción inválida, vuelva a seleccionar.");
                                }
                            }
                            convocatoriaAux.setEstado(estadoNuevo);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 7) {
                            System.out.print("Ingrese nueva fecha de apertura (YYYY-MM-DD): ");
                            java.sql.Date fechaAperturaNueva = java.sql.Date.valueOf(scanner.nextLine());
                            convocatoriaAux.setFecha_apertura(fechaAperturaNueva);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 8) {
                            System.out.print("Ingrese nueva fecha de cierre (YYYY-MM-DD): ");
                            java.sql.Date fechaCierreNueva = java.sql.Date.valueOf(scanner.nextLine());
                            convocatoriaAux.setFecha_cierre(fechaCierreNueva);
                            dao.editarConvocatoria(convocatoriaAux);
                            auxWhile1 = false;

                        } else if (opcionEditar == 9) {
                            // Listar rúbricas disponibles para cambiar la actual
                            RubricaDAOImpl rubricaDao = new RubricaDAOImpl();
                            LinkedList<Rubrica> rubricas = rubricaDao.listarRubricas();

                            System.out.println("Rúbricas disponibles:");
                            for (Rubrica r : rubricas) {
                                System.out.println(r.toString());
                            }

                            System.out.print("Ingrese el id de la nueva rúbrica: ");
                            int idRubricaNueva = scanner.nextInt();
                            scanner.nextLine();

                            Rubrica rubricaAuxiliarNueva = null;
                            for (Rubrica r : rubricas) {
                                if (r.getId_rubrica() == idRubricaNueva) {
                                    rubricaAuxiliarNueva = r;
                                    break;
                                }
                            }

                            if (rubricaAuxiliarNueva != null) {
                                convocatoriaAux.setRubrica(rubricaAuxiliarNueva);
                                dao.editarConvocatoria(convocatoriaAux);
                                System.out.println("Rúbrica actualizada correctamente.");
                            } else {
                                System.out.println("ID de rúbrica no encontrado.");
                            }
                            auxWhile1 = false;

                        } else if (opcionEditar == 10) {
                            // Lógica para editar los tipos de documentos asociados
                            TipoDocumentoDAOImpl daoTipoDocumento = new TipoDocumentoDAOImpl();
                            LinkedList<TipoDocumento> tipoDocumentos = daoTipoDocumento.buscarTipoDocumentos(idConvocatoria);


                            for(TipoDocumento tp: tipoDocumentos){
                                System.out.println(tp.toString());
                            }
                            System.out.println("Ingrese el id del tipo de documento a editar:");
                            int idDocumentoTipo = scanner.nextInt();
                            scanner.nextLine();

                            TipoDocumento tpAux = null;
                            for(TipoDocumento tp: tipoDocumentos){
                                if(tp.getId_TipoDocumento()==idDocumentoTipo){
                                    tpAux = tp;
                                    break;
                                }
                            }


                            System.out.println("Ingrese nombre nuevo del tipo de documento:");
                            String nuevoNombreDoc = scanner.nextLine();
                            tpAux.setNombre(nuevoNombreDoc);
                            daoTipoDocumento.editarTipoDocumento(tpAux);

                            auxWhile1 = false;

                        } else {
                            System.out.println("Opción inválida, intente nuevamente.");
                        }

                }



    }else if(numero==3){
        //listar convocatorias
        ConvocatoriaDAOImpl dao = new ConvocatoriaDAOImpl();
        LinkedList<Convocatoria> convocatorias  = dao.listarConvocatorias();
        for(Convocatoria c : convocatorias){
            System.out.println(c.toString());
        }

    }
    else if(numero==4){
        //listar convocatorias
        ConvocatoriaDAOImpl dao = new ConvocatoriaDAOImpl();
        LinkedList<Convocatoria> convocatorias  = dao.listarConvocatorias();
        for(Convocatoria c : convocatorias){
            System.out.println(c.toString());
        }
        // dar de baja convocatoria
        System.out.println("Ingrese el id de la convocatoria a dar de baja:");
        int auxid = scanner.nextInt();
        scanner.nextLine();

        dao.darDeBajaConvocatoria(auxid);
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
    scanner.nextLine();

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


    System.out.println("Usuario Evaluador Externo creado éxitosamente!");

    System.out.println(usuarioEvaluador.toString());

    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    dao.crearUsuarioEvaluador(usuarioEvaluador);


    scanner.close();
}

public void darDeBajaUsuario(){
    Scanner scanner = new Scanner(System.in);
    listarUsuarios();
    int id = scanner.nextInt();{}
    scanner.nextLine();
    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    dao.darDeBajaUsuario(id);
}

