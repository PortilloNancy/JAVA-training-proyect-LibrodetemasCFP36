package Entidades;

public class Caracter {

    private int idCaracter;
    private String descripcion;

    public int getIdCaracter() {
        return idCaracter;
    }

    public void setIdCaracter(int idCaracte) {
        this.idCaracter = idCaracte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
