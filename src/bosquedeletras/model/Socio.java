package bosquedeletras.model;

public class Socio {

	private int id;
	private String numeroSocio;
	private String fechaInscripcion;
	private boolean socioActivo;
	private String dniCliente; // FK → cliente.dni

	// Constructor parcial (por interfaz)
	public Socio(String numeroSocio, String fechaInscripcion, String dniCliente) {
		this.socioActivo = true;
		this.numeroSocio = numeroSocio;
		this.fechaInscripcion = fechaInscripcion;
		this.dniCliente = dniCliente;
	}

	// Constructor completo (desde BD)
	public Socio(int id, String numeroSocio, String fechaInscripcion, boolean socioActivo, String dniCliente) {
		this.id = id;
		this.numeroSocio = numeroSocio;
		this.fechaInscripcion = fechaInscripcion;
		this.socioActivo = socioActivo;
		this.dniCliente = dniCliente;
	}

	// Getters
	public int getId() {
		return id;
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

	public String getDniCliente() {
		return dniCliente;
	}

	// Setters
	public void setSocioActivo(boolean socioActivo) {
		this.socioActivo = socioActivo;
	}

	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	@Override
	public String toString() {
		return "Socio " + numeroSocio + " (cliente: " + dniCliente + ")";
	}
}
