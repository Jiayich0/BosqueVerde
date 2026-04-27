package bosquedeletras.model;

public class Socio extends Cliente {

	private String numeroSocio;
	private String fechaInscripcion;
	private boolean socioActivo;

	// Constructor parcial (por interfaz) — llama al constructor parcial de Cliente
	public Socio(String nombre, String primerApellido, String segundoApellido, String dni, String email,
			String numeroSocio, String fechaInscripcion) {
		super(nombre, primerApellido, segundoApellido, dni, email);
		this.numeroSocio = numeroSocio;
		this.fechaInscripcion = fechaInscripcion;
		this.socioActivo = true;
	}

	// Constructor completo (desde BD) — llama al constructor completo de Cliente
	public Socio(int id, String nombre, String primerApellido, String segundoApellido, String dni, String email,
			boolean activo, String numeroSocio, String fechaInscripcion, boolean socioActivo) {
		super(id, nombre, primerApellido, segundoApellido, dni, email, activo);
		this.numeroSocio = numeroSocio;
		this.fechaInscripcion = fechaInscripcion;
		this.socioActivo = socioActivo;
	}

	public String getNumeroSocio() {
		return numeroSocio;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public boolean isSocioActivo() {
		return socioActivo;
	}

	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public void setSocioActivo(boolean socioActivo) {
		this.socioActivo = socioActivo;
	}

	@Override
	public String toString() {
		return super.toString() + " — Socio nº " + numeroSocio;
	}
}