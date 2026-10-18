package uy.utec.pa.modelo;

public class Posgrado {
    private String nombre;
    private int id_Posgrado;
    private Institucion institucion;

    public Posgrado(String nombre, int id_Posgrado) {
        this.nombre = nombre;
        this.id_Posgrado = id_Posgrado;
    }

    public Posgrado(String nombre, int id_Posgrado,Institucion institucion) {
        this.nombre = nombre;
        this.id_Posgrado = id_Posgrado;
        this.institucion = institucion;
    }

    public Posgrado(String nombre ,Institucion institucion) {
        this.nombre = nombre;
        this.institucion = institucion;
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

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "Posgrado{" +
                "nombre='" + nombre + '\'' +
                ", id_Posgrado=" + id_Posgrado +
                ", institucion=" + institucion +
                '}';
    }
}
