package uy.utec.pa.modelo;

public class Usuario {
    private int id_usuario;
    private String ci;
    private String nacionalidad;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String calle;
    private int numero;
    private String estado;
    private  String correo;
    private String rol;

    public Usuario(int id_usuario, String ci, String nacionalidad, String contrasena, String nombre, String apellido, String calle, int numero, String estado, String correo, String rol) {
        this.id_usuario = id_usuario;
        this.ci = ci;
        this.nacionalidad = nacionalidad;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.numero = numero;
        this.estado = estado;
        this.correo = correo;
        this.rol=rol;
    }

    public Usuario(String ci, String nacionalidad, String contrasena, String nombre, String apellido, String calle, int numero, String estado, String correo, String rol) {
        this.ci = ci;
        this.nacionalidad = nacionalidad;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.numero = numero;
        this.estado = estado;
        this.correo = correo;
        this.rol=rol;
    }

    //SETTER Y GETTER
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



    //TOSTRING
    @Override
    public String toString() {
        return "Usuario{" +
                 id_usuario + "."+
                ", ci='" + ci + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", estado='" + estado + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
