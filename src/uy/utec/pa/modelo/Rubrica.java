package uy.utec.pa.modelo;

import java.util.LinkedList;

public class Rubrica {
    private String nombre;
    private int id_rubrica;
    private LinkedList<SeccionRubrica> seccionesRubrica;

    public Rubrica(String nombre, int id_rubrica, LinkedList<SeccionRubrica> seccionesRubrica) {
        this.nombre = nombre;
        this.id_rubrica = id_rubrica;
        this.seccionesRubrica = seccionesRubrica;
    }

    public Rubrica(String nombre, LinkedList<SeccionRubrica> seccionesRubrica) {
        this.nombre = nombre;
        this.seccionesRubrica = seccionesRubrica;
    }

    public Rubrica(int id_rubrica,String nombre){
        this.id_rubrica = id_rubrica;
        this.nombre = nombre;
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

    public LinkedList<SeccionRubrica> getSeccionesRubrica() {
        return seccionesRubrica;
    }

    public void setSeccionesRubrica(LinkedList<SeccionRubrica> seccionesRubrica) {
        this.seccionesRubrica = seccionesRubrica;
    }

    @Override
    public String toString() {
        return "Rubrica{" +
                "id_rubrica=" + id_rubrica + '\'' +
                ",nombre='" + nombre + '\'' +
                ", seccionesRubrica=" + seccionesRubrica +
                '}';
    }
}
