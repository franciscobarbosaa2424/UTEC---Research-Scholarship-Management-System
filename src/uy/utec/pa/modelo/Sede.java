package uy.utec.pa.modelo;

public class Sede {
    private String nombre;
    private int id_sede;

    public Sede(String nombre, int id_sede) {
        this.nombre = nombre;
        this.id_sede = id_sede;
    }

    public Sede(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "nombre='" + nombre + '\'' +
                ", id_sede=" + id_sede +
                '}';
    }
}
