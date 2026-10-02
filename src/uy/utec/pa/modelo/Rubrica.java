package uy.utec.pa.modelo;

public class Rubrica {
    private String nombre;
    private int id_rubrica;

    public Rubrica(String nombre, int id_rubrica) {
        this.nombre = nombre;
        this.id_rubrica = id_rubrica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_rubrica() {
        return id_rubrica;
    }

    public void setId_rubrica(int id_rubrica) {
        this.id_rubrica = id_rubrica;
    }

}
