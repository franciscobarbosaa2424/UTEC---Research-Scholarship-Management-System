package uy.utec.pa.modelo;

public class Evaluacion {
    private int id_Evaluacion;
    private String nombre;
    private String estado;
    private String juicio_global;
    private int puntaje_total;
    private String comentario_evaluador;
    private int puntaje;

    public Evaluacion(int id_Evaluacion, String nombre, String estado, String juicio_global, int puntaje_total, String comentario_evaluador, int puntaje) {
        this.id_Evaluacion = id_Evaluacion;
        this.nombre = nombre;
        this.estado = estado;
        this.juicio_global = juicio_global;
        this.puntaje_total = puntaje_total;
        this.comentario_evaluador = comentario_evaluador;
        this.puntaje = puntaje;
    }

    public int getId_Evaluacion() {
        return id_Evaluacion;
    }

    public void setId_Evaluacion(int id_Evaluacion) {
        this.id_Evaluacion = id_Evaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJuicio_global() {
        return juicio_global;
    }

    public void setJuicio_global(String juicio_global) {
        this.juicio_global = juicio_global;
    }

    public int getPuntaje_total() {
        return puntaje_total;
    }

    public void setPuntaje_total(int puntaje_total) {
        this.puntaje_total = puntaje_total;
    }

    public String getComentario_evaluador() {
        return comentario_evaluador;
    }

    public void setComentario_evaluador(String comentario_evaluador) {
        this.comentario_evaluador = comentario_evaluador;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

}
