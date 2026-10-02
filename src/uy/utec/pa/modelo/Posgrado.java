package uy.utec.pa.modelo;

public class Posgrado {
    private String nombre;
    private int id_Posgrado;

    public Posgrado(String nombre, int id_Posgrado) {
        this.nombre = nombre;
        this.id_Posgrado = id_Posgrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_Posgrado() {
        return id_Posgrado;
    }

    public void setId_Posgrado(int id_Posgrado) {
        this.id_Posgrado = id_Posgrado;
    }
}
