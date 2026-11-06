# SECU – Sistema de Evaluación de Convocatorias UTEC

**Proyecto de Desarrollo 2026 – UTEC | Equipo Bytech – Grupo 12**

---

## Descripción del proyecto

SECU es una aplicación de consola desarrollada en **Java**, orientada a la gestión de convocatorias, postulaciones y evaluaciones de la Dirección de Investigación y Desarrollo de UTEC.

El sistema permite administrar usuarios, controlar el acceso a funcionalidades según sus roles, gestionar postulaciones y realizar evaluaciones mediante evaluadores externos, rúbricas, secciones y criterios de puntuación.

La aplicación utiliza **PostgreSQL** para la persistencia de información y **JDBC** para la comunicación con la base de datos. Su implementación sigue principios de Programación Orientada a Objetos (POO) y utiliza patrones de diseño para organizar las responsabilidades del sistema.

## Módulos desarrollados

Los cuatro módulos desarrollados por el equipo son:

1. **Gestión de usuarios (CU03):** Administración de usuarios del sistema, incluyendo el registro, consulta y modificación de sus datos.
2. **Gestión de roles y acceso a funcionalidades (CU01):** Control del acceso a las diferentes operaciones del sistema según el rol del usuario.
3. **Gestión del proceso de evaluación (CU08 y CU09):** Administración de evaluaciones, asignación de evaluadores externos y gestión de rúbricas, secciones y criterios de evaluación.
4. **Gestión y revisión de postulaciones (CU07):** Consulta, revisión y actualización de la información y los estados de las postulaciones.

Los módulos se encuentran integrados mediante una base de datos compartida, permitiendo mantener la información utilizada durante los diferentes procesos.

## Patrones de diseño implementados

| Patrón | Aplicación | Ubicación en el proyecto |
|---|---|---|
| **DAO (Data Access Object)** | Separa la lógica de acceso a datos de las demás responsabilidades de la aplicación, encapsulando las operaciones de consulta, inserción, modificación y eliminación de registros en PostgreSQL. | Paquete `uy.utec.pa.dao`, mediante interfaces y sus implementaciones, como `RubricaDAO` y `RubricaDAOImpl`. |
| **Singleton** | Gestiona una instancia compartida para centralizar el acceso a la conexión con PostgreSQL. | Clase `ConexionBDSingleton`, ubicada en el paquete `uy.utec.pa.util`. |
| **Proxy** | Controla el acceso a las funcionalidades del sistema mediante la autenticación de usuarios, actuando como intermediario antes de delegar las operaciones al servicio real. | Clases `AutenticacionProxy` y `AutenticacionReal`, ubicadas en `uy.utec.pa.servicio`, utilizadas desde la clase `Main`. |
| **Factory Method** | Encapsula la creación de distintos tipos de usuarios, permitiendo instanciar administradores y evaluadores externos sin depender directamente de sus clases concretas desde el código cliente. | Paquete `uy.utec.pa.servicio`, mediante la clase abstracta `UsuarioFactory` y las clases concretas `AdministradorFactory` y `EvaluadorFactory`. |

La aplicación también utiliza clases del modelo de dominio ubicadas en `uy.utec.pa.modelo`, que representan entidades como usuarios, postulaciones, evaluaciones y rúbricas.

## Validaciones y manejo de excepciones

El sistema contempla validaciones sobre los datos ingresados y las reglas de negocio correspondientes a sus funcionalidades.

Entre las validaciones implementadas se incluyen:

- Validación de nombres y apellidos.
- Verificación del formato de correo electrónico.
- Comprobación de registros duplicados, como cédulas y correos electrónicos.
- Validación de los valores ingresados durante las evaluaciones.

Las operaciones de persistencia utilizan mecanismos de control de errores para tratar situaciones excepcionales durante la ejecución.

**Excepciones personalizadas:** No se implementaron excepciones personalizadas.

## Tecnologías utilizadas

| Tecnología | Utilización |
|---|---|
| **Java** | Lenguaje principal de desarrollo. |
| **PostgreSQL** | Sistema gestor de base de datos. |
| **JDBC** | Comunicación y operaciones de persistencia con PostgreSQL. |
| **Maven** | Gestión de dependencias y estructura del proyecto. |
| **Git y GitLab** | Control de versiones y trabajo colaborativo. |
| **IntelliJ IDEA** | Entorno de desarrollo utilizado. |

## Requisitos para ejecutar la aplicación

1. Tener instalado un JDK compatible con el proyecto. Durante el desarrollo se utilizó **Eclipse Temurin JDK 25**.
2. Disponer de **PostgreSQL** instalado y en funcionamiento.
3. Contar con **Maven** o un IDE que permita gestionar proyectos Maven.
4. Disponer del script SQL del proyecto para crear las tablas y demás objetos necesarios.
5. Configurar las variables de entorno para la conexión con PostgreSQL: `URL`, `USUARIO` y `CONTRASENA`.
6. Tener disponible el controlador JDBC de PostgreSQL mediante las dependencias del proyecto.

## Configuración de la base de datos

1. Crear una base de datos en PostgreSQL.
2. Ejecutar el script SQL incluido en el repositorio para generar la estructura correspondiente al esquema `proyecto`.
3. Cargar los datos iniciales necesarios para el funcionamiento de la aplicación, si corresponde.
4. Configurar las siguientes variables de entorno, utilizadas por la clase `ConexionBDSingleton` del paquete `uy.utec.pa.util`:

| Variable | Descripción |
|---|---|
| `URL` | URL JDBC de conexión a PostgreSQL. |
| `USUARIO` | Usuario de acceso a la base de datos. |
| `CONTRASENA` | Contraseña del usuario de PostgreSQL. |

La configuración debe coincidir con la instancia de PostgreSQL utilizada para ejecutar el sistema.

## Puesta en marcha

### 1. Clonar el repositorio

```bash
git clone https://git.utec.edu.uy/equipo_bytech/proyectodesarrollo_fbb.git
```

### 2. Abrir el proyecto

Abrir el proyecto en **IntelliJ IDEA** e importar la configuración de Maven mediante el archivo `pom.xml`.

### 3. Preparar la base de datos

Ejecutar el script SQL correspondiente en PostgreSQL y verificar que el esquema `proyecto` y sus tablas se hayan creado correctamente.

### 4. Configurar la conexión

Verificar que las variables de entorno `URL`, `USUARIO` y `CONTRASENA` estén correctamente configuradas para su utilización desde `ConexionBDSingleton`.

### 5. Configurar el correo electrónico

Configurar las variables de entorno necesarias para el servicio de envío de correos electrónicos, de acuerdo con la configuración utilizada en el proyecto.

### 6. Ejecutar la aplicación

Ejecutar la clase `Main` desde IntelliJ IDEA.

### 7. Utilizar el sistema

Interactuar con el menú de consola para acceder a las funcionalidades disponibles según el rol del usuario.

**Nota:** Para acceder a determinadas funcionalidades, es necesario contar con usuarios y registros iniciales en la base de datos.

## Seguridad de contraseñas

El sistema utiliza el algoritmo de hash **DJB2**, implementado en la clase `HashContrasena`, para transformar las contraseñas antes de almacenarlas en la base de datos.

DJB2 es una función de hash no reversible, por lo que no permite recuperar directamente la contraseña original a partir del valor almacenado.

**Importante:** Este mecanismo se implementó con fines académicos y no debe considerarse un sistema de almacenamiento seguro de contraseñas para entornos de producción.

## Equipo de desarrollo

**Bytech – Grupo 12**  
Licenciatura en Tecnologías de la Información – UTEC

### Integrantes

- Gael Russo
- Mauro Amado
- Francisco Barbosa
- Benjamín Cáceres
- Mateo Rodríguez
