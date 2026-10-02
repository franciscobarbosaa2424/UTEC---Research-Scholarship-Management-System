package uy.utec.pa.modelo;

public class TutorUtec {
    private String nombre;
    private String apellido;
    private  String departamento;
    private  String correo_utec;

    public TutorUtec(String nombre, String apellido, String departamento, String correo_utec) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.correo_utec = correo_utec;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCorreo_utec() {
        return correo_utec;
    }

    public void setCorreo_utec(String correo_utec) {
        this.correo_utec = correo_utec;
    }
}
