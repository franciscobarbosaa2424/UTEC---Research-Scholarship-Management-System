package uy.utec.pa.modelo;

public class Postulante {
    private String documento;
    private String pais_procedencia;
    private String departamento_procedencia;
    private String localidad_procedencia;
    private int id_Postulante;
    private String correo;
    private String nombre;
    private String apellidos;
    private boolean esDocenteUtec;
    private String nacionalidad;
    private String actividad_laboral;
    private int celular;
    private String calle;
    private String apartamento;

    public Postulante(String documento, String pais_procedencia, String departamento_procedencia, String localidad_procedencia, int id_Postulante, String correo, String nombre, String apellidos, boolean esDocenteUtec, String nacionalidad, String actividad_laboral, int celular, String calle, String apartamento) {
        this.documento = documento;
        this.pais_procedencia = pais_procedencia;
        this.departamento_procedencia = departamento_procedencia;
        this.localidad_procedencia = localidad_procedencia;
        this.id_Postulante = id_Postulante;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esDocenteUtec = esDocenteUtec;
        this.nacionalidad = nacionalidad;
        this.actividad_laboral = actividad_laboral;
        this.celular = celular;
        this.calle = calle;
        this.apartamento = apartamento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPais_procedencia() {
        return pais_procedencia;
    }

    public void setPais_procedencia(String pais_procedencia) {
        this.pais_procedencia = pais_procedencia;
    }

    public String getDepartamento_procedencia() {
        return departamento_procedencia;
    }

    public void setDepartamento_procedencia(String departamento_procedencia) {
        this.departamento_procedencia = departamento_procedencia;
    }

    public String getLocalidad_procedencia() {
        return localidad_procedencia;
    }

    public void setLocalidad_procedencia(String localidad_procedencia) {
        this.localidad_procedencia = localidad_procedencia;
    }

    public int getId_Postulante() {
        return id_Postulante;
    }

    public void setId_Postulante(int id_Postulante) {
        this.id_Postulante = id_Postulante;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isEsDocenteUtec() {
        return esDocenteUtec;
    }

    public void setEsDocenteUtec(boolean esDocenteUtec) {
        this.esDocenteUtec = esDocenteUtec;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getActividad_laboral() {
        return actividad_laboral;
    }

    public void setActividad_laboral(String actividad_laboral) {
        this.actividad_laboral = actividad_laboral;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

}
