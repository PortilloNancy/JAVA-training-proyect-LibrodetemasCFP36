package Entidades;

public class Provincia {

    private int idProvincia;
    private String nombre;

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int id) {
        this.idProvincia = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
