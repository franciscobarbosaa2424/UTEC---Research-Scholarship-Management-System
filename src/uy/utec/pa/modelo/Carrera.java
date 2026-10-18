package uy.utec.pa.modelo;

public class Carrera {
    private String nombre;
    private int id_Carrera;
    private Institucion institucion;

    public Carrera(String nombre, int id_Carrera,Institucion institucion) {
        this.nombre = nombre;
        this.id_Carrera = id_Carrera;
        this.institucion = institucion;
    }

    public Carrera(String nombre,Institucion institucion) {
        this.nombre = nombre;
        this.institucion = institucion;
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

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", id_Carrera=" + id_Carrera +
                ", institucion=" + institucion +
                '}';
    }
}
