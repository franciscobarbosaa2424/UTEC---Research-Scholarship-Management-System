package uy.utec.pa.modelo;

public class Carrera {
    private String nombre;
    private int id_Carrera;

    public Carrera(String nombre, int id_Carrera) {
        this.nombre = nombre;
        this.id_Carrera = id_Carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_Carrera() {
        return id_Carrera;
    }

    public void setId_Carrera(int id_Carrera) {
        this.id_Carrera = id_Carrera;
    }

}
