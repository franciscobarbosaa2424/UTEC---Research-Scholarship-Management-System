package uy.utec.pa.dao;

import uy.utec.pa.modelo.DocumentoAdjunto;
import uy.utec.pa.modelo.TipoDocumento;

public interface DocumentoAdjuntoDAO {

    void crearDocumentoAjunto(DocumentoAdjunto documentoAdjunto);
    int buscarDocumentoAdjuntoPorNombre(String nombre);
    void editarDocumentoAdjunto(DocumentoAdjunto documentoAdjunto);
    void eliminarDocumentoAdjunto(int id_documentoAdjunto);


}
