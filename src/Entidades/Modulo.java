package Entidades;

public class Modulo {

    private int idModulo;
    private String descripcion;
    private boolean borrado;

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
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

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean desabilitado) {
        this.borrado = desabilitado;
    }

}
