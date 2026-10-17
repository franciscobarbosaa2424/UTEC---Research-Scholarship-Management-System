package uy.utec.pa.dao;

import uy.utec.pa.modelo.TipoDocumento;

public interface TipoDocumentoDAO {
    void crearTipoDocumento(TipoDocumento tipoDocumento);
    void editarTipoDocumento(TipoDocumento tipoDocumento);
    void eliminarTipoDocumento(int id_tipoDocumento);
    int buscarTipoDocumentoPorNombre(String nombre);
    void asignarTipoDocumentoConvocatoria(int idConvocatoria,int idTipoDocumento);

}
