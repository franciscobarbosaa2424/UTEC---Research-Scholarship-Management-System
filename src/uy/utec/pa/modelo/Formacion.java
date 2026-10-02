package uy.utec.pa.modelo;

public class Formacion {

    private int id_formacion;
    private String nombre_formacion;

    public Formacion(int id_formacion, String nombre_formacion) {
        this.id_formacion = id_formacion;
        this.nombre_formacion = nombre_formacion;
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
}
