import uy.utec.pa.dao.*;
import uy.utec.pa.modelo.*;
import uy.utec.pa.servicio.*;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;


    public void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Menú Principal ===");
        System.out.println("1. Realizar Postulación a una convocatoria");
        System.out.println("2. Loguearse como Administrador/Evaluador Externo");
        int opcionMenuPrincipal= scanner.nextInt();
        scanner.nextLine();

         if(opcionMenuPrincipal==1){
             MenuPostulante();

         }else if(opcionMenuPrincipal==2) {
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
                     for (Usuario u : usuarios) {
                         if (u.getCorreo().equalsIgnoreCase(correo)) {
                             rol = u.getRol();
                         }
                     }
                     if (rol.equalsIgnoreCase("administrador")) {
                         System.out.println("Bienvenido Administrador");
                         MenuPrincipalAdmin();

                     } else {
                         if (rol.equalsIgnoreCase("evaluador")) {
                             //aca iria el menu cuando el acceso es correcto pero no es administrador
                         }
                     }

                 } else {
                     System.out.println("Correo o contraseña incorrectos.");
                 }
         }
            System.out.println();
            System.out.println();

        scanner.close();
    }

    public void MenuPrincipalAdmin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido. ¿Qué desea realizar?");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Convocatorias");
        System.out.println("3. Gestión de Rúbricas");
        System.out.println("4. Gestión de Tutores");
        System.out.println("5. Gestión de Sedes");
        System.out.println("6. Gestión de Departamentos Academicos");
        System.out.println("7. Gestión de Postulaciones");
        System.out.print("Ingrese la opción a realizar: ");

        int numero = scanner.nextInt();


        if(numero==1){
            MenuUsuario();
        }else if(numero==2){
            GestionConvocatorias();
        }else if(numero==3){
            MenuRubricas();
        } else if (numero==4) {
            MenuTutores();
        } else if (numero==5) {
            MenuSede();
        } else if (numero==6) {
            MenuDepartamentoAcademico();
        } else if (numero==7) {
            MenuPostulaciones();
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

                                            if (tpAux == null) {
                                                System.out.println("No se encontró ese tipo de documento en la convocatoria.");
                                            } else {
                                                System.out.println("¿Qué desea hacer?");
                                                System.out.println("1. Editar el nombre");
                                                System.out.println("2. Quitar el documento de la convocatoria");

                                                int accion = scanner.nextInt();
                                                scanner.nextLine();

                                                if (accion == 1) {
                                                    System.out.println("Ingrese el nuevo nombre del tipo de documento:");
                                                    String nuevoNombreDoc = scanner.nextLine();

                                                    tpAux.setNombre(nuevoNombreDoc);
                                                    daoTipoDocumento.editarTipoDocumento(tpAux);

                                                    System.out.println("Tipo de documento editado correctamente.");

                                                } else if (accion == 2) {
                                                    daoTipoDocumento.eliminarTipoDocumento(idDocumentoTipo);

                                                    System.out.println("Tipo de documento quitado de la convocatoria.");

                                                } else {
                                                    System.out.println("Opción inválida.");
                                                }

                                                auxWhile1 = false;

                                            }

                        }else{
                            System.out.println("Error, vuelva a ingresar una opción");
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



public void MenuPostulante(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== Menú Postulante ===");
    System.out.println();
    System.out.println("Ingrese el ID de la Convocatoria que desea postularse:");

    ConvocatoriaDAOImpl dao = new ConvocatoriaDAOImpl();
    LinkedList<Convocatoria> convocatorias  = dao.listarConvocatorias();
    for(Convocatoria c : convocatorias){
        System.out.println(c.toString());
    }

    int idConvocatoria = scanner.nextInt();
    scanner.nextLine();

    //aca
    System.out.println("Documento:");
    String documento = scanner.nextLine();

    System.out.println("Nombre:");
    String nombre = scanner.nextLine();

    System.out.println("Apellido:");
    String apellido = scanner.nextLine();

    System.out.println("Correo:");
    String correo = scanner.nextLine();

    System.out.println("Nacionalidad:");
    String nacionalidad = scanner.nextLine();

    System.out.println("Actividad laboral:");
    String actLaboral = scanner.nextLine();

    boolean esDocUtec;
    while (true) {
        System.out.println("¿Es docente de UTEC? (1.Sí o 2.No): ");
        int seleccionEstado = scanner.nextInt();
        scanner.nextLine();

        if (seleccionEstado == 1) {
            esDocUtec = true;
            break;
        } else if (seleccionEstado == 2) {
            esDocUtec = false;
            break;
        } else {
            System.out.println("Opción inválida, vuelva a seleccionar.");
        }
    }

    System.out.println("Calle:");
    String calle = scanner.nextLine();

    System.out.println("Número:");
    int numero = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Apartamento:");
    String apartamento = scanner.nextLine();

    System.out.println("Bis:");
    String bis = scanner.nextLine();

    System.out.println("Localidad de procedencia:");
    String locProcedencia = scanner.nextLine();

    System.out.println("Departamento de procedencia:");
    String depProcedencia = scanner.nextLine();

    System.out.println("País de procedencia:");
    String paiProcedencia = scanner.nextLine();

//BUCLE PARA CELULARES
    boolean auxWhile=true;
    System.out.println("Celulares del postulante");
    LinkedList<String> celulares = new LinkedList<>();
    while (auxWhile==true){
        System.out.println("Ingresar celular (ingrese solamente 0 para salir):");
        String celular = scanner.nextLine();

        if(celular.equalsIgnoreCase("0")){
            auxWhile=false;
            break;
        }

        celulares.add(celular);

    }

    Postulante postulante = new Postulante(documento, paiProcedencia, depProcedencia, locProcedencia, correo, nombre, apellido, esDocUtec, nacionalidad, actLaboral, celulares, calle, apartamento,  bis, numero);
    PostulanteDAOImpl daoPostulante = new PostulanteDAOImpl();
    daoPostulante.crearPostulante(postulante);
    int idPostulanteGenerado = daoPostulante.buscarPostulantePorDocumento(postulante.getDocumento());
    postulante.setId_Postulante(idPostulanteGenerado);


    MenuFormacion(postulante);

    scanner.close();

}


public void MenuFormacion(Postulante postulante){
    Scanner scanner = new Scanner(System.in);
    System.out.println();
    System.out.println("Ingrese nombre de su formacion:");
    String nombreFormacion = scanner.nextLine();


    boolean carreraAgregada = false;
    boolean posgradoAgregado = false;
    int opcionFormacion;
    int idCarreraGenerado = 0;
    int idPosgradoGenerado = 0;
    int idFormacionGenerado = 0;


    Formacion formacion = new Formacion(nombreFormacion,postulante);
    FormacionDAOImpl dao = new FormacionDAOImpl();
    idFormacionGenerado = dao.crearFormacion(formacion);

    do {
        System.out.println("--- Gestión de la Formación: " + nombreFormacion + "¿Qué desea ingresar?");

        if (carreraAgregada==false) {
            System.out.println("1. Carrera");
        }
        if (posgradoAgregado==false) {
            System.out.println("2. Posgrado");
        }
        System.out.println("0. Terminar y Salir de Formaciones");

        opcionFormacion = scanner.nextInt();
        scanner.nextLine();

        if (opcionFormacion == 1 && carreraAgregada==false) {
            System.out.println("Ingrese Nombre de Carrera:");
            String nombreCarrera = scanner.nextLine();

            //llamamos al metodo auxiliar en vez de hacer el socotroco de recursividad q hicimos
            Institucion institucion = gestionarInstitucion(scanner);

            if (institucion != null) {
                Carrera carrera = new Carrera(nombreCarrera, institucion);
                CarreraDAOImpl daoCarrera = new CarreraDAOImpl();
                idCarreraGenerado =  daoCarrera.crearCarrera(carrera);
                carreraAgregada = true;
                System.out.println("Carrera agregada con exito");
            }
        }
        else if (opcionFormacion == 2 && posgradoAgregado==false) {
            System.out.println("Ingrese Nombre del Posgrado:");
            String nombrePosgrado = scanner.nextLine();

            Institucion institucion = gestionarInstitucion(scanner);

            if (institucion != null) {
                Posgrado posgrado = new Posgrado(nombrePosgrado, institucion);
                PosgradoDAOImpl daoPosgrado = new PosgradoDAOImpl();
                idPosgradoGenerado = daoPosgrado.crearPosgrado(posgrado);
                posgradoAgregado = true;
                System.out.println("Posgrado agregado con exito");
            }
        }
        else if (opcionFormacion != 0) {
            System.out.println("la opción es inválida o ya fue ingresada en esta formación.");
        }

        if(opcionFormacion == 1) {
            dao.asignarFormacionCarrera(idFormacionGenerado, idCarreraGenerado);
        }
        if(opcionFormacion == 2) {
            dao.asignarFormacionPosgrado(idFormacionGenerado, idPosgradoGenerado);
        }

        // El bucle sigue mientras no elija salir y falte agregar al menos una de las dos cosas
    } while (opcionFormacion != 0 && (carreraAgregada==false || posgradoAgregado==false));

}

    //aca le mande un metodo auxiliar para evitar recursividad,
    //le mande private para q solo lo llame menuformacion es medio al dope pero no esta de mas
    private Institucion gestionarInstitucion(Scanner scanner) {
        System.out.println("Instituciones disponibles: ");
        InstitucionDAOImpl daoInstitucion = new InstitucionDAOImpl();
        LinkedList<Institucion> instituciones = daoInstitucion.listarInstituciones();

        for (Institucion i : instituciones) {
            System.out.println(i.toString());
        }

        System.out.println("Ingrese ID de su Institución (o presione 0 para agregar una nueva):");
        int idInstitucion = scanner.nextInt();
        scanner.nextLine();

        Institucion institucion = null;

        if (idInstitucion == 0) {
            System.out.println("Ingrese el nombre de la institución:");
            String institucionNombre = scanner.nextLine();
            institucion = new Institucion(institucionNombre);
            daoInstitucion.crearInstitucion(institucion);
        } else {
            for (Institucion i : instituciones) {
                if (i.getId_Institucion() == idInstitucion) {
                    institucion = i;
                    break;
                }
            }
        }
        return institucion;
    }


    public void MenuTutores(){
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestión de Tutores:");
        System.out.println("1. Crear Tutor");
        System.out.println("2. Editar Tutor");
        System.out.println("3. Listar Tutores");
        System.out.println("4. Eliminar un Tutor");
        System.out.print("Ingrese la opción a realizar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero == 1){

            System.out.println("Ingrese correo de UTEC:");
            String correo = scanner.nextLine();
            System.out.println("Ingrese nombre:");
            String nombre= scanner.nextLine();
            System.out.println("Ingrese apellido:");
            String apellido= scanner.nextLine();
            System.out.println("Ingrese departamento(área):");
            String dep_area = scanner.nextLine();

            TutorUtec tutor = new TutorUtec(nombre,apellido,dep_area,correo);
            TutorDAOImpl dao = new TutorDAOImpl();
            dao.crearTutor(tutor);

        } else if (numero==2) {
            TutorDAOImpl dao = new TutorDAOImpl();
            LinkedList<TutorUtec> tutores = dao.listarTutores();

            for(TutorUtec tutor: tutores){
                System.out.println(tutor.toString());
            }

            System.out.println("Ingrese el ID del tutor que desea editar:");
            int idTutor = scanner.nextInt();
            scanner.nextLine();

            TutorUtec tutorAux = null;
            for(TutorUtec tutor: tutores){
                if(tutor.getId_tutor()==idTutor){
                   tutorAux = tutor;
                   break;
                }
            }

            System.out.println("Seleccione el campo que desea editar:");
            System.out.println("1. Nombre: "+tutorAux.getNombre());
            System.out.println("2. Apellido: "+tutorAux.getApellido());
            System.out.println("3. Correo UTEC: "+tutorAux.getCorreo_utec());
            System.out.println("4. Departamento(área): "+tutorAux.getDepartamento());

            int opcionEditar = scanner.nextInt();
            scanner.nextInt();
            scanner.nextLine();

            if(opcionEditar==1){
                System.out.println("Ingrese nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                tutorAux.setNombre(nuevoNombre);

            } else if (opcionEditar==2) {
                System.out.println("Ingrese nuevo apellido:");
                String nuevoApellido = scanner.nextLine();
                tutorAux.setApellido(nuevoApellido);
            }else if (opcionEditar==3) {
                System.out.println("Ingrese nuevo correo UTEC:");
                String nuevoCorreo = scanner.nextLine();
                tutorAux.setCorreo_utec(nuevoCorreo);
            }else if (opcionEditar==4) {
                System.out.println("Ingrese nuevo departamento:");
                String nuevoDepartamento = scanner.nextLine();
                tutorAux.setDepartamento(nuevoDepartamento);
            }

            dao.editarTutor(tutorAux);


        } else if (numero==3) {
            //listar
            TutorDAOImpl dao = new TutorDAOImpl();
            LinkedList<TutorUtec> tutores = dao.listarTutores();

            for(TutorUtec tutor: tutores){
                System.out.println(tutor.toString());
            }


        } else if (numero==4) {
            //listar
            TutorDAOImpl dao = new TutorDAOImpl();
            LinkedList<TutorUtec> tutores = dao.listarTutores();

            for(TutorUtec tutor: tutores){
                System.out.println(tutor.toString());
            }

            //eliminarle segun id por scanner
            System.out.println("Digite id del tutor a eliminar: ");
            int idtutotAux = scanner.nextInt();
            scanner.nextLine();

            dao.eliminarTutor(idtutotAux);
        }


        scanner.close();
    }


    public void MenuSede(){
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestión de Sedes:");
        System.out.println("1. Crear Sede");
        System.out.println("2. Editar Sede");
        System.out.println("3. Listar Sede");
        System.out.println("4. Eliminar una Sede");
        System.out.print("Ingrese la opción a realizar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero == 1){

            System.out.println("Ingrese nombre:");
            String nombre= scanner.nextLine();

            Sede sede = new Sede(nombre);
            SedeDAOImpl dao = new SedeDAOImpl();
            dao.crearSede(sede);

        } else if (numero==2) {
            SedeDAOImpl dao = new SedeDAOImpl();
            LinkedList<Sede> sedes = dao.listarSede();

            for(Sede s: sedes){
                System.out.println(s.toString());
            }

            System.out.println("Ingrese el ID de la sede que desea editar:");
            int idSede = scanner.nextInt();
            scanner.nextLine();

            Sede sedeAux = null;
            for(Sede s: sedes){
                if(s.getId_sede()==idSede){
                    sedeAux = s;
                    break;
                }
            }

            System.out.println("Seleccione el campo que desea editar:");
            System.out.println("1. Nombre: "+sedeAux.getNombre());

            int opcionEditar = scanner.nextInt();
            scanner.nextLine();

            if(opcionEditar==1){
                System.out.println("Ingrese nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                sedeAux.setNombre(nuevoNombre);
            }

            dao.editarSede(sedeAux);


        } else if (numero==3) {
            //listar
            SedeDAOImpl dao = new SedeDAOImpl();
            LinkedList<Sede> sedes = dao.listarSede();

            for(Sede s: sedes){
                System.out.println(s.toString());
            }


        } else if (numero==4) {
            //listar
            SedeDAOImpl dao = new SedeDAOImpl();
            LinkedList<Sede> sedes = dao.listarSede();

            for(Sede s: sedes){
                System.out.println(s.toString());
            }

            //eliminarle segun id por scanner
            System.out.println("Digite id de la sede a eliminar: ");
            int idSedeAux = scanner.nextInt();
            scanner.nextLine();

            dao.eliminarSede(idSedeAux);
        }

        scanner.close();
    }


    public void MenuDepartamentoAcademico(){
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestión de Sedes:");
        System.out.println("1. Crear Departamento Academico");
        System.out.println("2. Editar Departamento Academico");
        System.out.println("3. Listar Departamento Academico");
        System.out.println("4. Eliminar un Departamento Academico");
        System.out.print("Ingrese la opción a realizar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero == 1){
            System.out.println("Ingrese nombre:");
            String nombre= scanner.nextLine();

            DepartamentoAcademico departamentoAcademico = new DepartamentoAcademico(nombre);
            DepaAcademicoDAOImpl dao = new DepaAcademicoDAOImpl();
            dao.crearDepaAcademico(departamentoAcademico);

        } else if (numero==2) {
            DepaAcademicoDAOImpl dao = new DepaAcademicoDAOImpl();
            LinkedList<DepartamentoAcademico> departamentos = dao.listarDepartamentoAcademico();

            for(DepartamentoAcademico d: departamentos){
                System.out.println(d.toString());
            }

            System.out.println("Ingrese el ID del departamento que desea editar:");
            int idDep = scanner.nextInt();
            scanner.nextLine();

            DepartamentoAcademico depAux = null;
            for(DepartamentoAcademico d: departamentos){
                if(d.getId_DepartamentoAcademico()==idDep){
                    depAux = d;
                    break;
                }
            }

            System.out.println("Seleccione el campo que desea editar:");
            System.out.println("1. Nombre: "+depAux.getNombre());

            int opcionEditar = scanner.nextInt();
            scanner.nextLine();

            if(opcionEditar==1){
                System.out.println("Ingrese nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                depAux.setNombre(nuevoNombre);
            }

            dao.editarDepaAcademico(depAux);


        } else if (numero==3) {
            //listar
            DepaAcademicoDAOImpl dao = new DepaAcademicoDAOImpl();
            LinkedList<DepartamentoAcademico> departamentos = dao.listarDepartamentoAcademico();

            for(DepartamentoAcademico d: departamentos){
                System.out.println(d.toString());
            }


        } else if (numero==4) {
            //listar
            DepaAcademicoDAOImpl dao = new DepaAcademicoDAOImpl();
            LinkedList<DepartamentoAcademico> departamentoAcademicos = dao.listarDepartamentoAcademico();

            for(DepartamentoAcademico d: departamentoAcademicos){
                System.out.println(d.toString());
            }

            //eliminarle segun id por scanner
            System.out.println("Digite id del departamento academico a eliminar: ");
            int idDepaAcademico = scanner.nextInt();
            scanner.nextLine();

            dao.eliminarDepaAcademico(idDepaAcademico);
        }

        scanner.close();
    }


    public void MenuPostulaciones(){

        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestión de Postulacion:");
        System.out.println("1. Crear Postulacion");
        System.out.println("2. Editar Postulacion");
        System.out.println("3. Listar Postulacion");
        System.out.println("4. Eliminar una Postulacion");
        System.out.print("Ingrese la opción a realizar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        PostulacionDAOImpl dao = new PostulacionDAOImpl();


        if (numero == 1){
            // CREAR POSTULACIÓN
            System.out.println("Ingrese nombre de la postulación:");
            String nombre = scanner.nextLine();

            PostulanteDAOImpl postulanteDao = new PostulanteDAOImpl();
            LinkedList<Postulante> postulantes = postulanteDao.listarPostulantes();

            System.out.println("Ingrese el ID del postulante que desea seleccionar:");
            for(Postulante p: postulantes){
                System.out.println(p.toString());
            }

            int idPostulante = scanner.nextInt();
            scanner.nextLine();

            Postulante postulanteAux = null;

            for(Postulante p: postulantes){
               if(p.getId_Postulante()==idPostulante){
                   postulanteAux=p;
                   break;
               }
            }


            System.out.println("Ingrese cantidad de meses:");
            int canMes = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese línea de investigación:");
            String lineaInvestigacion = scanner.nextLine();

            System.out.println("Ingrese la observacion del CES:");
            String cesObservacion = scanner.nextLine();

            System.out.println("Ingrese el nombre del primer grupo de investigacion:");
            String nomGrupo1 = scanner.nextLine();

            System.out.println("Ingrese el nombre del segundo grupo de investigacion:");
            String nomGrupo2 = scanner.nextLine();

            System.out.println("Ingrese el orden final del CES:");
            String ordenFinalCes = scanner.nextLine();

            System.out.println("Ingrese el estado de la postulacion:");
            String estado = scanner.nextLine();

            DepaAcademicoDAOImpl daoDepaAcademico = new DepaAcademicoDAOImpl();
            LinkedList<DepartamentoAcademico> departamentoAcademicos = daoDepaAcademico.listarDepartamentoAcademico();
            System.out.println("Ingrese el ID del departamento Academico: ");

            for(DepartamentoAcademico dep : departamentoAcademicos){
                System.out.println(dep.toString());
            }

            int idDepaAcademico= scanner.nextInt();
            scanner.nextLine();

            DepartamentoAcademico depAux = null;

            for(DepartamentoAcademico dep : departamentoAcademicos){
                if(dep.getId_DepartamentoAcademico()==idDepaAcademico){
                    depAux = dep;
                    break;
                }
            }


            SedeDAOImpl daoSede = new SedeDAOImpl();
            LinkedList<Sede> sedes = daoSede.listarSede();
            System.out.println("ingrese el id de la sede: ");

            for(Sede s : sedes){
                System.out.println(s.toString());
            }

            int idSede = scanner.nextInt();
            scanner.nextLine();

            Sede sedeAux = null;

            for(Sede s : sedes){
                if(s.getId_sede()==idSede){
                    sedeAux=s;
                    break;
                }
            }

            Postulacion nuevaPostulacion = new Postulacion();
            nuevaPostulacion.setNombre_postulacion(nombre);
            nuevaPostulacion.setCantidad_meses(canMes);
            nuevaPostulacion.setLinea_Investigacion(lineaInvestigacion);
            nuevaPostulacion.setObservacion_CES(cesObservacion);
            nuevaPostulacion.setGrupoInvestigacion(nomGrupo1);
            nuevaPostulacion.setOtroGrupoInvestigacion(nomGrupo2);
            nuevaPostulacion.setOrden_final(ordenFinalCes);
            nuevaPostulacion.setFecha_solicitud(java.time.LocalDate.now());
            nuevaPostulacion.setEstado(estado);
            nuevaPostulacion.setDepartamentoAcademico(depAux);
            nuevaPostulacion.setSede(sedeAux);
            nuevaPostulacion.setPostulante(postulanteAux);


            dao.crearPostulacion(nuevaPostulacion);

        } else if (numero == 2) {

            LinkedList<Postulacion> postulaciones = dao.listarPostulaciones();

            for(Postulacion p : postulaciones){
                System.out.println("ID: " + p.getId_postulacion() + " - Nombre: " + p.getNombre_postulacion());
            }

            System.out.println("Ingrese el ID de la postulación que desea editar:");
            int idPost = scanner.nextInt();
            scanner.nextLine();

            Postulacion postAux = null;
            for(Postulacion p : postulaciones){
                if(p.getId_postulacion() == idPost){
                    postAux = p;
                    break;
                }
            }

            if (postAux != null) {
                System.out.println("Seleccione el campo que desea editar:");
                System.out.println("1. Nombre: " + postAux.getNombre_postulacion());
                System.out.println("2. Cantidad de meses: " + postAux.getCantidad_meses());
                System.out.println("3. Línea de Investigación: " + postAux.getLinea_Investigacion());
                System.out.println("4. Observación del CES: " + postAux.getObservacion_CES());
                System.out.println("5. Grupo de investigación 1: " + postAux.getGrupoInvestigacion());
                System.out.println("6. Grupo de investigación 2: " + postAux.getOtroGrupoInvestigacion());
                System.out.println("7. Orden final del CES: " + postAux.getOrden_final());
                System.out.println("8. Estado: " + postAux.getEstado());

                int opcionEditar = scanner.nextInt();
                scanner.nextLine();

                if(opcionEditar == 1){
                    System.out.println("Ingrese nuevo nombre:");
                    postAux.setNombre_postulacion(scanner.nextLine());
                } else if(opcionEditar == 2){
                    System.out.println("Ingrese nueva cantidad de meses:");
                    postAux.setCantidad_meses(scanner.nextInt());
                    scanner.nextLine();
                } else if(opcionEditar == 3){
                    System.out.println("Ingrese nueva línea de investigación:");
                    postAux.setLinea_Investigacion(scanner.nextLine());
                } else if(opcionEditar == 4){
                    System.out.println("Ingrese nueva observación del CES:");
                    postAux.setObservacion_CES(scanner.nextLine());
                } else if(opcionEditar == 5){
                    System.out.println("Ingrese nuevo nombre del primer grupo de investigación:");
                    postAux.setGrupoInvestigacion(scanner.nextLine());
                } else if(opcionEditar == 6){
                    System.out.println("Ingrese nuevo nombre del segundo grupo de investigación:");
                    postAux.setOtroGrupoInvestigacion(scanner.nextLine());
                } else if(opcionEditar == 7){
                    System.out.println("Ingrese nuevo orden final del CES:");
                    postAux.setOrden_final(scanner.nextLine());
                } else if(opcionEditar == 8){
                    System.out.println("Ingrese nuevo estado:");
                    postAux.setEstado(scanner.nextLine());
                } else {
                    System.out.println("Opción inválida.");
                    return;
                }

                dao.editarPostulacion(postAux);
                System.out.println("¡Postulación actualizada con éxito!");
            } else {
                System.out.println("No se encontró una postulación con ese ID.");
            }

        } else if (numero == 3) {
            LinkedList<Postulacion> postulaciones = dao.listarPostulaciones();

            for(Postulacion p : postulaciones){
                System.out.println("ID: " + p.getId_postulacion() +
                        " | Nombre: " + p.getNombre_postulacion() +
                        " | Estado: " + p.getEstado() +
                        " | Línea: " + p.getLinea_Investigacion());
            }

        } else if (numero == 4) {
            // listar
            LinkedList<Postulacion> postulaciones = dao.listarPostulaciones();
            for(Postulacion p : postulaciones){
                System.out.println("ID: " + p.getId_postulacion() + " - Nombre: " + p.getNombre_postulacion());
            }

            //eliminar
            System.out.println("Digite el ID de la postulación a eliminar: ");
            int idPostulacion = scanner.nextInt();
            scanner.nextLine();

            dao.eliminarPostulacion(idPostulacion);
        }

        scanner.close();
    }