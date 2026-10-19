package uy.utec.pa.modelo;

public class DepartamentoAcademico {

    private String nombre;
    private int id_DepartamentoAcademico;

    public DepartamentoAcademico(String nombre, int id_DepartamentoAcademico) {
        this.nombre = nombre;
        this.id_DepartamentoAcademico = id_DepartamentoAcademico;
    }

    public DepartamentoAcademico(String nombre) {
        this.nombre = nombre;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_DepartamentoAcademico() {
        return id_DepartamentoAcademico;
    }

    public void setId_DepartamentoAcademico(int id_DepartamentoAcademico) {
        this.id_DepartamentoAcademico = id_DepartamentoAcademico;
    }

    @Override
    public String toString() {
        return "DepartamentoAcademico{" +
                "nombre='" + nombre + '\'' +
                ", id_DepartamentoAcademico=" + id_DepartamentoAcademico +
                '}';
    }
}
