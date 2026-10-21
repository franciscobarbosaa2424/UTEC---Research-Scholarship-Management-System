package uy.utec.pa.modelo;

public class EvaluadorExterno extends Usuario {

    private String institucion;

    public EvaluadorExterno(int id_usuario, String ci, String nacionalidad, String contrasena, String nombre, String apellido, String calle, int numero, String estado, String correo, String rol, String institucion) {
        super(id_usuario, ci, nacionalidad, contrasena, nombre, apellido, calle, numero, estado, correo, rol);
        this.institucion = institucion;
    }

    public EvaluadorExterno(String ci, String nacionalidad, String contrasena, String nombre, String apellido, String calle, int numero, String estado, String correo, String rol, String institucion) {
        super(ci, nacionalidad, contrasena, nombre, apellido, calle, numero, estado, correo, rol);
        this.institucion = institucion;
    }

    // Constructor adaptado exactamente al orden que usas en el DAO para listar
    public EvaluadorExterno(String correo, String nombre, String apellido, String calle, String nacionalidad, String ci, String contrasena, String estado, String rol, String institucion) {
        super(ci, nacionalidad, contrasena, nombre, apellido, calle, 0, estado, correo, rol); // Le pasamos 0 por defecto al número si no viene en esta query
        this.institucion = institucion;
    }


    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "EvaluadorExterno{" +
                "usuario='" + super.toString() + '\'' +
                ", institucion='" + institucion + '\'' +
                '}';
    }
}
