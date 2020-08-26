package Entidades;

public class Sede {

    private int idSede;
    private String descripcion;
    private String direccion;
    private String telefono;
    private boolean borrada;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    public boolean isBorrada() {
        return borrada;
    }

    public void setBorrada(boolean borrada) {
        this.borrada = borrada;
    }


}
