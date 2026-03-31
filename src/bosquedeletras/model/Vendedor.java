package bosquedeletras.model;

public class Vendedor {

	private int id;
	private boolean activo;

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String dni;
	private String telefono;

	// Constructor parcial (por interfaz)
	public Vendedor(String nombre, String primerApellido, String segundoApellido, String dni, String telefono) {
		this.activo = true;

		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = (segundoApellido == null || segundoApellido.isBlank()) ? null : segundoApellido;
		this.dni = dni;
		this.telefono = telefono;
	}

	// Constructor completo (desde BD)
	public Vendedor(int id, String nombre, String primerApellido, String segundoApellido, String dni, String telefono,
			boolean activo) {
		this.id = id;
		this.activo = activo;

		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = (segundoApellido == null || segundoApellido.isBlank()) ? null : segundoApellido;
		this.dni = dni;
		this.telefono = telefono;
	}

	// Getters
	public int getId() {
		return id;
	}

	public boolean isActivo() {
		return activo;
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

	public String getTelefono() {
		return telefono;
	}

	// Setters
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// toString útil para debug
	@Override
	public String toString() {
		String segApell = segundoApellido == null ? "" : " " + segundoApellido;
		return nombre + " " + primerApellido + segApell + " (" + dni + ")";
	}
}
