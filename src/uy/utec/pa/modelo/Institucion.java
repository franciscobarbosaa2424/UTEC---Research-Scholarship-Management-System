package uy.utec.pa.modelo;

public class Institucion {
    private String nombre;
    private int id_Institucion;

    public Institucion(String nombre, int id_Institucion) {
        this.nombre = nombre;
        this.id_Institucion = id_Institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_Institucion() {
        return id_Institucion;
    }

    public void setId_Institucion(int id_Institucion) {
        this.id_Institucion = id_Institucion;
    }

}
