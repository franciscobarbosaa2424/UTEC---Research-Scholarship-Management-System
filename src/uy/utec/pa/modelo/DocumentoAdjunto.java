package uy.utec.pa.modelo;

public class DocumentoAdjunto {
    private String nombre;
    private String rutaArchivo;
    private int id_DocumentoAdjunto;
    private TipoDocumento tipoDocumento;
    private Postulacion postulacion;


    public DocumentoAdjunto(String nombre, String rutaArchivo, int id_DocumentoAdjunto, TipoDocumento tipoDocumento, Postulacion postulacion) {
        this.nombre = nombre;
        this.rutaArchivo = rutaArchivo;
        this.id_DocumentoAdjunto = id_DocumentoAdjunto;
        this.tipoDocumento = tipoDocumento;
        this.postulacion = postulacion;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }


    @Override
    public String toString() {
        return "DocumentoAdjunto{" +
                "nombre='" + nombre + '\'' +
                ", rutaArchivo='" + rutaArchivo + '\'' +
                ", id_DocumentoAdjunto=" + id_DocumentoAdjunto +
                ", tipoDocumento=" + tipoDocumento +
                ", postulacion=" + postulacion +
                '}';
    }
}
