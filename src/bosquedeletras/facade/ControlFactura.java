package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOFactura;
import bosquedeletras.dao.DAOLineaFactura;
import bosquedeletras.model.Factura;
import bosquedeletras.model.LineaFactura;

public class ControlFactura {

	private DAOFactura daoFactura;
	private DAOLineaFactura daoLineaFactura;

	public ControlFactura() {
		this.daoFactura = new DAOFactura();
		this.daoLineaFactura = new DAOLineaFactura();
	}

	public int abrirVenta(String fecha, int idCliente, int idVendedor) {
		Factura nuevaFactura = new Factura(fecha, idCliente, idVendedor);
		return daoFactura.insertar(nuevaFactura);
	}

	public boolean anadirLibroAVenta(int idFactura, int idLibro, int cantidad, double precioUnitario) {
		Factura factura = daoFactura.buscarPorId(idFactura);

		if (factura == null || factura.isCerrada()) {
			return false;
		}

		LineaFactura nuevaLinea = new LineaFactura(idFactura, idLibro, cantidad, precioUnitario);
		boolean insertada = daoLineaFactura.insertar(nuevaLinea);

		if (!insertada) {
			return false;
		}

		double nuevoTotal = calcularTotal(idFactura);
		factura.setTotal(nuevoTotal);

		return daoFactura.actualizar(factura);
	}

	public boolean cerrarVenta(int idFactura) {
		Factura factura = daoFactura.buscarPorId(idFactura);

		if (factura == null || factura.isCerrada()) {
			return false;
		}

		List<LineaFactura> lineas = daoLineaFactura.listarPorFactura(idFactura);
		if (lineas == null || lineas.isEmpty()) {
			return false;
		}

		double total = calcularTotal(idFactura);
		factura.setTotal(total);
		factura.setCerrada(true);

		return daoFactura.actualizar(factura);
	}

	public double calcularTotal(int idFactura) {
		List<LineaFactura> lineas = daoLineaFactura.listarPorFactura(idFactura);

		double total = 0.0;
		for (LineaFactura linea : lineas) {
			total += linea.getSubtotal();
		}

		return total;
	}

	public Factura leerFactura(int idFactura) {
		return daoFactura.buscarPorId(idFactura);
	}

	public List<LineaFactura> leerLineasFactura(int idFactura) {
		return daoLineaFactura.listarPorFactura(idFactura);
	}

	public List<Factura> listarFacturas() {
		return daoFactura.listar();
	}

	public List<Factura> listarFacturasPorCliente(int idCliente) {
		return daoFactura.listarPorCliente(idCliente);
	}

	public List<Factura> listarFacturasPorVendedor(int idVendedor) {
		return daoFactura.listarPorVendedor(idVendedor);
	}
}
