package bosquedeletras.model;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean activo;

    public Categoria(String nombre, String descripcion) {  
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }

    public Categoria(int id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
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