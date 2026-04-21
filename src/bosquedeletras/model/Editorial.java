package bosquedeletras.model;

public class Editorial {

	private String id;
	private boolean activo;
	private String nombre;

	// Constructor parcial (por interfaz) - NUEVA editorial
	public Editorial(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.activo = true;
	}

	// Constructor completo (desde BD)
	public Editorial(String id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	// Getters
	public String getId() {
		return id;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getNombre() {
		return nombre;
	}

	// Setters
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " - " + nombre;
	}
}