package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOCliente;
import bosquedeletras.dao.DAOFactura;
import bosquedeletras.dao.DAOLibro;
import bosquedeletras.dao.DAOLineaFactura;
import bosquedeletras.dao.DAOVendedor;
import bosquedeletras.model.Cliente;
import bosquedeletras.model.Factura;
import bosquedeletras.model.Libro;
import bosquedeletras.model.LineaFactura;
import bosquedeletras.model.Vendedor;

public class ControlFactura {

	private DAOFactura daoFactura;
	private DAOLineaFactura daoLineaFactura;
	private DAOCliente daoCliente;
	private DAOVendedor daoVendedor;
	private DAOLibro daoLibro;


	public ControlFactura() {
		this.daoFactura = new DAOFactura();
		this.daoLineaFactura = new DAOLineaFactura();
		this.daoCliente = new DAOCliente();
		this.daoVendedor = new DAOVendedor();
		this.daoLibro = new DAOLibro();
	}

	public int abrirVenta(String fecha, int idCliente, int idVendedor) {
		if (fecha == null || fecha.isBlank()) return -1;

		Cliente cliente = daoCliente.buscarPorDni(idCliente);
		if (cliente == null) return -1;

		Vendedor vendedor = daoVendedor.buscarPorDni(idVendedor);
		if (vendedor == null) return -1;

		Factura nuevaFactura = new Factura(fecha, idCliente, idVendedor);
		return daoFactura.insertar(nuevaFactura);
	}

	public boolean anadirLibroAVenta(int idFactura, int idLibro, int cantidad, double precioUnitario) {
		Factura factura = daoFactura.buscarPorId(idFactura);
		if (factura == null || factura.isCerrada()) return false;

		Libro libro = daoLibro.buscarPorIsbn(idLibro);
		if (libro == null) return false;

		if (cantidad <= 0 || precioUnitario <= 0) return false;

		LineaFactura nuevaLinea = new LineaFactura(idFactura, idLibro, cantidad, precioUnitario);
		if (!daoLineaFactura.insertar(nuevaLinea)) return false;

		factura.setTotal(calcularTotal(idFactura));
		return daoFactura.actualizar(factura);
	}

	public boolean cerrarVenta(int idFactura) {
		Factura factura = daoFactura.buscarPorId(idFactura);
		if (factura == null || factura.isCerrada()) return false;

		if (daoCliente.buscarPorDni(factura.getIdCliente()) == null) return false;
		if (daoVendedor.buscarPorDni(factura.getIdVendedor()) == null) return false;

		List<LineaFactura> lineas = daoLineaFactura.listarPorFactura(idFactura);
		if (lineas == null || lineas.isEmpty()) return false;

		double total = calcularTotal(idFactura);
		if (total <= 0) return false;

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
		if (daoCliente.buscarPorId(idCliente) == null) return List.of();
		return daoFactura.listarPorCliente(idCliente);
	}

	public List<Factura> listarFacturasPorVendedor(int idVendedor) {
		if (daoVendedor.buscarPorId(idVendedor) == null) return List.of();
		return daoFactura.listarPorVendedor(idVendedor);
	}
}
