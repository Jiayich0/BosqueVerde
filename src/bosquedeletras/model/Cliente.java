package bosquedeletras.model;

import bosquedeletras.strategy.Sortable;

public class Cliente implements Sortable {

	private int id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String dni;
	private String email;
	private boolean activo;

	// Constructor parcial (por interfaz)
	public Cliente(String nombre, String primerApellido, String segundoApellido, String dni, String email) {
		this.activo = true;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = (segundoApellido == null || segundoApellido.isBlank()) ? null : segundoApellido;
		this.dni = dni;
		this.email = (email == null || email.isBlank()) ? null : email;
	}

	// Constructor completo (desde BD)
	public Cliente(int id, String nombre, String primerApellido, String segundoApellido, String dni, String email,
			boolean activo) {
		this.id = id;
		this.activo = activo;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = (segundoApellido == null || segundoApellido.isBlank()) ? null : segundoApellido;
		this.dni = dni;
		this.email = (email == null || email.isBlank()) ? null : email;
	}

	// Getters
	@Override
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActivo() {
		return activo;
	}

	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		String segApell = segundoApellido == null ? "" : " " + segundoApellido;
		return nombre + " " + primerApellido + segApell + " (" + dni + ")";
	}
}
