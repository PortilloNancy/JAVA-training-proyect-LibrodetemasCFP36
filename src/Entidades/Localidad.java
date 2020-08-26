package Entidades;

public class Localidad {

    private long idLocalidad;
    private String nombre;
    private String non_departamento;

    @Override
    public String toString() {
        return nombre + " / " + non_departamento;
    }
    private Provincia provincia;

    public long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNon_departamento() {
        return non_departamento;
    }

    public void setNon_departamento(String non_departamento) {
        this.non_departamento = non_departamento;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}
