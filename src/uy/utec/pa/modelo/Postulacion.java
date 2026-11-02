package uy.utec.pa.modelo;

import java.time.LocalDate;
import java.util.LinkedList;

public class Postulacion {
    private int id_postulacion;
    private String localidad_procedencia;
    private int cantidad_meses;
    private String nombre_postulacion;
    private LocalDate fecha_solicitud;
    private String observacion_CES;
    private String orden_final;
    private String orden_precalculado;
    private String linea_Investigacion;
    private String estado;
    private String grupoInvestigacion;
    private String otroGrupoInvestigacion;
    private LinkedList<DocumentoAdjunto> documentoAdjunto;
    private DepartamentoAcademico departamentoAcademico;
    private Sede sede;
    private Postulante postulante;
    private Convocatoria convocatoria;


    public Postulacion(int id_postulacion, String localidad_procedencia, int cantidad_meses, String nombre_postulacion, LocalDate fecha_solicitud, String observacion_CES, String orden_final, String orden_precalculado, String linea_Investigacion, String estado, LinkedList<DocumentoAdjunto> documentoAdjunto, DepartamentoAcademico departamentoAcademico, Sede sede, Postulante postulante,String grupoInvestigacion,String otroGrupoInvestigacion,Convocatoria convocatoria) {
        this.id_postulacion = id_postulacion;
        this.localidad_procedencia = localidad_procedencia;
        this.cantidad_meses = cantidad_meses;
        this.nombre_postulacion = nombre_postulacion;
        this.fecha_solicitud = fecha_solicitud;
        this.observacion_CES = observacion_CES;
        this.orden_final = orden_final;
        this.orden_precalculado = orden_precalculado;
        this.linea_Investigacion = linea_Investigacion;
        this.estado = estado;
        this.documentoAdjunto = documentoAdjunto;
        this.departamentoAcademico = departamentoAcademico;
        this.sede = sede;
        this.postulante = postulante;
        this.grupoInvestigacion=grupoInvestigacion;
        this.otroGrupoInvestigacion=otroGrupoInvestigacion;
        this.convocatoria=convocatoria;
    }

    public Postulacion() {

    }

    public int getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(int id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public String getLocalidad_procedencia() {
        return localidad_procedencia;
    }

    public void setLocalidad_procedencia(String localidad_procedencia) {
        this.localidad_procedencia = localidad_procedencia;
    }

    public int getCantidad_meses() {
        return cantidad_meses;
    }

    public void setCantidad_meses(int cantidad_meses) {
        this.cantidad_meses = cantidad_meses;
    }

    public String getNombre_postulacion() {
        return nombre_postulacion;
    }

    public void setNombre_postulacion(String nombre_postulacion) {
        this.nombre_postulacion = nombre_postulacion;
    }

    public LocalDate getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDate fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getObservacion_CES() {
        return observacion_CES;
    }

    public void setObservacion_CES(String observacion_CES) {
        this.observacion_CES = observacion_CES;
    }

    public String getOrden_final() {
        return orden_final;
    }

    public void setOrden_final(String orden_final) {
        this.orden_final = orden_final;
    }

    public String getOrden_precalculado() {
        return orden_precalculado;
    }

    public void setOrden_precalculado(String orden_precalculado) {
        this.orden_precalculado = orden_precalculado;
    }

    public String getLinea_Investigacion() {
        return linea_Investigacion;
    }

    public void setLinea_Investigacion(String linea_Investigacion) {
        this.linea_Investigacion = linea_Investigacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LinkedList<DocumentoAdjunto> getDocumentoAdjunto() {
        return documentoAdjunto;
    }

    public void setDocumentoAdjunto(LinkedList<DocumentoAdjunto> documentoAdjunto) {
        this.documentoAdjunto = documentoAdjunto;
    }

    public DepartamentoAcademico getDepartamentoAcademico() {
        return departamentoAcademico;
    }

    public void setDepartamentoAcademico(DepartamentoAcademico departamentoAcademico) {
        this.departamentoAcademico = departamentoAcademico;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }


    public Postulante getPostulante() {
        return postulante;
    }
    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public String getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(String grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public String getOtroGrupoInvestigacion() {
        return otroGrupoInvestigacion;
    }

    public void setOtroGrupoInvestigacion(String otroGrupoInvestigacion) {
        this.otroGrupoInvestigacion = otroGrupoInvestigacion;
    }

    public Convocatoria getConvocatoria(){
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria){
        this.convocatoria=convocatoria;
    }

    @Override
    public String toString() {
        return "Postulacion{" +
                "id_postulacion=" + id_postulacion +
                ", localidad_procedencia='" + localidad_procedencia + '\'' +
                ", cantidad_meses=" + cantidad_meses +
                ", nombre_postulacion='" + nombre_postulacion + '\'' +
                ", fecha_solicitud=" + fecha_solicitud +
                ", observacion_CES='" + observacion_CES + '\'' +
                ", orden_final='" + orden_final + '\'' +
                ", orden_precalculado='" + orden_precalculado + '\'' +
                ", linea_Investigacion='" + linea_Investigacion + '\'' +
                ", estado='" + estado + '\'' +
                ", grupoInvestigacion='" + grupoInvestigacion + '\'' +
                ", otroGrupoInvestigacion='" + otroGrupoInvestigacion + '\'' +
                ", documentoAdjunto=" + documentoAdjunto +
                ", departamentoAcademico=" + departamentoAcademico +
                ", sede=" + sede +
                ", postulante=" + postulante +
                '}';
    }
}
