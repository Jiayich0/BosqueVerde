package bosquedeletras.model;

public class LineaFactura {

	private int id;

	private int idFactura;
	private int idLibro;
	private int cantidad;
	private double precioUnitario;
	private double subtotal;

	// Constructor parcial (por interfaz)
	public LineaFactura(int idFactura, int idLibro, int cantidad, double precioUnitario) {
		this.idFactura = idFactura;
		this.idLibro = idLibro;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = cantidad * precioUnitario;
	}

	// Constructor completo (desde BD)
	public LineaFactura(int id, int idFactura, int idLibro, int cantidad, double precioUnitario, double subtotal) {
		this.id = id;
		this.idFactura = idFactura;
		this.idLibro = idLibro;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}

	// Getters
	public int getId() {
		return id;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public double getSubtotal() {
		return subtotal;
	}

	// Setters
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.subtotal = this.cantidad * this.precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
		this.subtotal = this.cantidad * this.precioUnitario;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double calcularSubtotal() {
		this.subtotal = this.cantidad * this.precioUnitario;
		return this.subtotal;
	}

	// toString útil para debug
	@Override
	public String toString() {
		return "LineaFactura #" + id + " | Factura: " + idFactura + " | Libro: " + idLibro
				+ " | Cantidad: " + cantidad + " | Precio unitario: " + precioUnitario
				+ " | Subtotal: " + subtotal;
	}
}
