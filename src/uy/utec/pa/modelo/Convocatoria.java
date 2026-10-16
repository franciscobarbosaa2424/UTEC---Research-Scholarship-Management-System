package uy.utec.pa.modelo;

import java.time.LocalDate;
import java.sql.Date;
import java.util.LinkedList;

public class Convocatoria {
    private int idConvocatoria;
    private String nombre;
    private int anio;
    private String descripcion;
    private int cantidadBecas;
    private String tipoBeca;
    private String estado;
    private Date fecha_apertura;
    private Date fecha_cierre;
    private Rubrica rubrica;
    private LinkedList<TipoDocumento> tipoDocumento;


    public Convocatoria(int idConvocatoria, String nombre, int anio, String descripcion, int cantidadBecas, String tipoBeca, String estado, Date fecha_apertura, Date fecha_cierre, Rubrica rubrica, LinkedList<TipoDocumento> tipoDocumento) {
        this.idConvocatoria = idConvocatoria;
        this.nombre = nombre;
        this.anio = anio;
        this.descripcion = descripcion;
        this.cantidadBecas = cantidadBecas;
        this.tipoBeca = tipoBeca;
        this.estado = estado;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
        this.rubrica = rubrica;
        this.tipoDocumento = tipoDocumento;
    }

    public Convocatoria(String nombre, int anio, String descripcion, int cantidadBecas, String tipoBeca, String estado, Date fecha_apertura, Date fecha_cierre, Rubrica rubrica, LinkedList<TipoDocumento> tipoDocumento) {
        this.nombre = nombre;
        this.anio = anio;
        this.descripcion = descripcion;
        this.cantidadBecas = cantidadBecas;
        this.tipoBeca = tipoBeca;
        this.estado = estado;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
        this.rubrica = rubrica;
        this.tipoDocumento = tipoDocumento;
    }

    public int getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(int idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadBecas() {
        return cantidadBecas;
    }

    public void setCantidadBecas(int cantidadBecas) {
        this.cantidadBecas = cantidadBecas;
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    public LinkedList<TipoDocumento> getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(LinkedList<TipoDocumento> tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
