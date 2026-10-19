package uy.utec.pa.modelo;

import java.util.LinkedList;

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
    private LinkedList<String> celulares;
    private String calle;
    private String apartamento;
    private String bis;
    private int numeroCasa;

    public Postulante(String documento, String pais_procedencia, String departamento_procedencia, String localidad_procedencia, int id_Postulante, String correo, String nombre, String apellidos, boolean esDocenteUtec, String nacionalidad, String actividad_laboral, LinkedList<String> celulares, String calle, String apartamento, String bis, int numeroCasa) {
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
        this.celulares = celulares;
        this.calle = calle;
        this.apartamento = apartamento;
        this.bis=bis;
        this.numeroCasa=numeroCasa;
    }

    public Postulante(String documento, String pais_procedencia, String departamento_procedencia, String localidad_procedencia, String correo, String nombre, String apellidos, boolean esDocenteUtec, String nacionalidad, String actividad_laboral, LinkedList<String> celulares, String calle, String apartamento, String bis, int numeroCasa) {
        this.documento = documento;
        this.pais_procedencia = pais_procedencia;
        this.departamento_procedencia = departamento_procedencia;
        this.localidad_procedencia = localidad_procedencia;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esDocenteUtec = esDocenteUtec;
        this.nacionalidad = nacionalidad;
        this.actividad_laboral = actividad_laboral;
        this.celulares = celulares;
        this.calle = calle;
        this.apartamento = apartamento;
        this.bis = bis;
        this.numeroCasa = numeroCasa;
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

    public boolean esDocenteUtec() {
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

    public LinkedList<String> getCelulares() {
        return celulares;
    }

    public void setCelulares(LinkedList<String> celulares) {
        this.celulares = celulares;
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


    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }



}
