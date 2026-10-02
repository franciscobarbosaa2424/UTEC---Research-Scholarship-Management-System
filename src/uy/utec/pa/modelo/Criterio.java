package uy.utec.pa.modelo;

public class Criterio {
    private int idCriterio;
    private String nombre;
    private SeccionRubrica seccionRubrica;

    public Criterio(int idCriterio, String nombre, SeccionRubrica seccionRubrica) {
        this.idCriterio = idCriterio;
        this.nombre = nombre;
        this.seccionRubrica = seccionRubrica;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SeccionRubrica getSeccionRubrica() {
        return seccionRubrica;
    }

    public void setSeccionRubrica(SeccionRubrica seccionRubrica) {
        this.seccionRubrica = seccionRubrica;
    }
}
