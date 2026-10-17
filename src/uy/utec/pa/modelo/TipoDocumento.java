package uy.utec.pa.modelo;

public class TipoDocumento {
    private String nombre;
    private int id_TipoDocumento;

    public TipoDocumento(String nombre, int id_TipoDocumento) {
        this.nombre = nombre;
        this.id_TipoDocumento = id_TipoDocumento;
    }

    public TipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_TipoDocumento() {
        return id_TipoDocumento;
    }

    public void setId_TipoDocumento(int id_TipoDocumento) {
        this.id_TipoDocumento = id_TipoDocumento;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                " id_TipoDocumento=" + id_TipoDocumento + '\'' +
                "nombre='" + nombre +
                '}';
    }

}
