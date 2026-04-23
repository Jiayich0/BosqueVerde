package bosquedeletras.model;

import bosquedeletras.strategy.Sortable;

public class Editorial implements Sortable {

	private int id;
	private String id_editorial;
	private boolean activo;
	private String nombre;

	// Constructor parcial (por interfaz) - NUEVA editorial
	public Editorial(String id_editorial, String nombre) {
		this.id_editorial = id_editorial;
		this.nombre = nombre;
		this.activo = true;
	}

	// Constructor completo (desde BD)
	public Editorial(int id, String id_editorial, String nombre, boolean activo) {
		this.id = id;
		this.id_editorial = id_editorial;
		this.nombre = nombre;
		this.activo = activo;
	}

	// Getters
	@Override
	public int getId() {
		return id;
	}

	public String getIdEditorial() {
		return id_editorial;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getNombre() {
		return nombre;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setIdEditorial(String id_editorial) {
		this.id_editorial = id_editorial;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return id_editorial + " - " + nombre;
	}
}