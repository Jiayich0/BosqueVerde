package bosquedeletras.model;

public class Categoria {

    private int id;
    private boolean activo;

    private String nombre;
    private String descripcion;

    public Categoria(String nombre, String descripcion) {
        this.activo = true;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria(int id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.activo = activo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}