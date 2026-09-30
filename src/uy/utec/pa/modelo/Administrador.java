package uy.utec.pa.modelo;

import java.util.Date;

public class Administrador extends Usuario{

    private String admin_Permiso;
    private String puesto;
    private Date fecha_alta;

    public Administrador(int id_usuario, String ci, String nacionalidad, String contrasena, String nombre, String apellido, String calle, int numero, String estado, String correo, String admin_Permiso, String puesto, Date fecha_alta) {
        super(id_usuario,ci,nacionalidad,contrasena,nombre,apellido,calle,numero,estado,correo);
        this.admin_Permiso=admin_Permiso;
        this.puesto=puesto;
        this.fecha_alta= fecha_alta;
    }

    public String getAdmin_Permiso() {
        return admin_Permiso;
    }

    public void setAdmin_Permiso(String admin_Permiso) {
        this.admin_Permiso = admin_Permiso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "admin_Permiso='" + admin_Permiso + '\'' +
                ", puesto='" + puesto + '\'' +
                ", fecha_alta=" + fecha_alta +
                '}';
    }
}
