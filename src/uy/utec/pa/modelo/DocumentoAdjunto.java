package uy.utec.pa.modelo;

public class DocumentoAdjunto {
    private String nombre;
    private String rutaArchivo;
    private int id_DocumentoAdjunto;

    public DocumentoAdjunto(String nombre, String rutaArchivo, int id_DocumentoAdjunto) {
        this.nombre = nombre;
        this.rutaArchivo = rutaArchivo;
        this.id_DocumentoAdjunto = id_DocumentoAdjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public int getId_DocumentoAdjunto() {
        return id_DocumentoAdjunto;
    }

    public void setId_DocumentoAdjunto(int id_DocumentoAdjunto) {
        this.id_DocumentoAdjunto = id_DocumentoAdjunto;
    }


}
