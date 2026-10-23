package uy.utec.pa.modelo;

public class Evaluacion {
    private int id_Evaluacion;
    private String nombre;
    private String estado;
    private String juicio_global;
    private double puntaje_total;
    private String comentario_evaluador;
    private EvaluadorExterno evaluadorExterno;
    private int id_Postulacion;


    public Evaluacion(int id_Evaluacion, String nombre, String estado, String juicio_global, double puntaje_total, String comentario_evaluador) {
        this.id_Evaluacion = id_Evaluacion;
        this.nombre = nombre;
        this.estado = estado;
        this.juicio_global = juicio_global;
        this.puntaje_total = puntaje_total;
        this.comentario_evaluador = comentario_evaluador;
    }

    public Evaluacion(String nombre, String estado, String juicio_global, double puntaje_total, String comentario_evaluador) {
        this.nombre = nombre;
        this.estado = estado;
        this.juicio_global = juicio_global;
        this.puntaje_total = puntaje_total;
        this.comentario_evaluador = comentario_evaluador;
    }

    public Evaluacion(int id_Postulacion, EvaluadorExterno evaluadorExterno, String comentario_evaluador, double puntaje_total, String juicio_global, String estado, String nombre, int id_Evaluacion) {
        this.id_Postulacion = id_Postulacion;
        this.evaluadorExterno = evaluadorExterno;
        this.comentario_evaluador = comentario_evaluador;
        this.puntaje_total = puntaje_total;
        this.juicio_global = juicio_global;
        this.estado = estado;
        this.nombre = nombre;
        this.id_Evaluacion = id_Evaluacion;
    }

    public Evaluacion(){}

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

    public double getPuntaje_total() {
        return puntaje_total;
    }

    public void setPuntaje_total(double puntaje_total) {
        this.puntaje_total = puntaje_total;
    }

    public String getComentario_evaluador() {
        return comentario_evaluador;
    }

    public void setComentario_evaluador(String comentario_evaluador) {
        this.comentario_evaluador = comentario_evaluador;
    }

    public EvaluadorExterno getEvaluadorExterno() {
        return evaluadorExterno;
    }

    public void setEvaluadorExterno(EvaluadorExterno evaluadorExterno) {
        this.evaluadorExterno = evaluadorExterno;
    }



    public int getId_Postulacion() {
        return id_Postulacion;
    }

    public void setId_Postulacion(int id_Postulacion) {
        this.id_Postulacion = id_Postulacion;
    }


    @Override
    public String toString() {
        return "Evaluacion{" +
                "id_Evaluacion=" + id_Evaluacion +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", juicio_global='" + juicio_global + '\'' +
                ", puntaje_total=" + puntaje_total +
                ", comentario_evaluador='" + comentario_evaluador + '\'' +
                ", evaluadorExterno=" + evaluadorExterno +
                ", id_Postulacion=" + id_Postulacion +
                '}';
    }
}
