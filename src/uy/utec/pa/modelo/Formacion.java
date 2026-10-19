package uy.utec.pa.modelo;

public class Formacion {

    private int id_formacion;
    private String nombre_formacion;
    private Postulante postulante;

    public Formacion(int id_formacion, String nombre_formacion,Postulante postulante) {
        this.id_formacion = id_formacion;
        this.nombre_formacion = nombre_formacion;
        this.postulante=postulante;
    }

    public Formacion(String nombre_formacion,Postulante postulante) {
        this.nombre_formacion = nombre_formacion;
        this.postulante=postulante;
    }

    public int getId_formacion() {
        return id_formacion;
    }

    public void setId_formacion(int id_formacion) {
        this.id_formacion = id_formacion;
    }

    public String getNombre_formacion() {
        return nombre_formacion;
    }

    public void setNombre_formacion(String nombre_formacion) {
        this.nombre_formacion = nombre_formacion;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }
}
