package uy.utec.pa.modelo;

public class Valoracion {
    private double puntaje;
    private Criterio criterio;
    private int idEvaluacion;
    private int idPostulacion;
    private int idUsuario;


    public Valoracion(double puntaje, Criterio criterio, int idPostulacion, int idEvaluacion, int idUsuario) {
        this.puntaje = puntaje;
        this.criterio = criterio;
        this.idPostulacion = idPostulacion;
        this.idEvaluacion = idEvaluacion;
        this.idUsuario = idUsuario;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public void setEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }


    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    @Override
    public String toString() {
        return "Valoracion{" +
                "puntaje=" + puntaje +
                ", criterio=" + criterio +
                ", idEvaluacion=" + idEvaluacion +
                ", idPostulacion=" + idPostulacion +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
