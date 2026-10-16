package uy.utec.pa.modelo;

public class SeccionRubrica {
    private int id_secRubrica;
    private String nombre;
    private double peso;

    public SeccionRubrica(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public SeccionRubrica(String nombre, double peso, int id_secRubrica) {
        this.id_secRubrica = id_secRubrica;
        this.nombre = nombre;
        this.peso = peso;
    }

    public int getId_secRubrica() {
        return id_secRubrica;
    }

    public void setId_secRubrica(int id_secRubrica) {
        this.id_secRubrica = id_secRubrica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "SeccionRubrica{" +
                "id_secRubrica = "+ id_secRubrica + '\'' +
                "nombre='" + nombre + '\'' +
                ", peso=" + peso +
                '}';
    }
}