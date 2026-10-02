package uy.utec.pa.modelo;

public class DepartamentoAcademico {

    private String nombre;
    private int id_DepartamentoAcademico;
    private String postulacion;

    public DepartamentoAcademico(String nombre, int id_DepartamentoAcademico, String postulacion) {
        this.nombre = nombre;
        this.id_DepartamentoAcademico = id_DepartamentoAcademico;
        this.postulacion = postulacion;
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

    public String getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(String postulacion) {
        this.postulacion = postulacion;
    }

}
