package bosquedeletras.model;

import bosquedeletras.strategy.Sortable;

public class Factura implements Sortable {

	private int id;

	private String fecha;
	private double total;
	private boolean cerrada;
	private int idCliente;
	private int idVendedor;

	// Constructor parcial (por interfaz)
	public Factura(String fecha, int idCliente, int idVendedor) {
		this.fecha = fecha;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.total = 0.0;
		this.cerrada = false;
	}

	// Constructor completo (desde BD)
	public Factura(int id, String fecha, double total, boolean cerrada, int idCliente, int idVendedor) {
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.cerrada = cerrada;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
	}

	// Getters
	@Override
	public int getId() {
		return id;
	}

	public String getFecha() {
		return fecha;
	}

	public double getTotal() {
		return total;
	}

	public boolean isCerrada() {
		return cerrada;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	// Setters
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	// toString útil para debug
	@Override
	public String toString() {
		return "Factura #" + id + " | Fecha: " + fecha + " | Cliente: " + idCliente
				+ " | Vendedor: " + idVendedor + " | Total: " + total
				+ " | Cerrada: " + cerrada;
	}
}
