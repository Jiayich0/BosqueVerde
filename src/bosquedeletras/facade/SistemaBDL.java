package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.model.Vendedor;

import bosquedeletras.model.Factura;
import bosquedeletras.model.LineaFactura;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;
	private ControlFactura controlFactura;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
		this.controlLibro = new ControlLibro();
		this.controlFactura = new ControlFactura();
	}

	public static SistemaBDL getInstance() {
		if (instancia == null) {
			instancia = new SistemaBDL();
		}
		return instancia;
	}
	
	public ControlVendedor getControlVendedor() {
		return controlVendedor;
	}

	public ControlLibro getControlLibro() {
		return controlLibro;
	}

	public boolean abrirVenta(String fecha, int idCliente, int idVendedor) {
		return controlFactura.abrirVenta(fecha, idCliente, idVendedor);
	}

	public boolean anadirLibroAVenta(int idFactura, int idLibro, int cantidad, double precioUnitario) {
		return controlFactura.anadirLibroAVenta(idFactura, idLibro, cantidad, precioUnitario);
	}

	public boolean cerrarVenta(int idFactura) {
		return controlFactura.cerrarVenta(idFactura);
	}

	public double calcularTotal(int idFactura) {
		return controlFactura.calcularTotal(idFactura);
	}

	public Factura leerFactura(int idFactura) {
		return controlFactura.leerFactura(idFactura);
	}

	public List<LineaFactura> leerLineasFactura(int idFactura) {
		return controlFactura.leerLineasFactura(idFactura);
	}

	public List<Factura> listarFacturas() {
		return controlFactura.listarFacturas();
	}

	public List<Factura> listarFacturasPorCliente(int idCliente) {
		return controlFactura.listarFacturasPorCliente(idCliente);
	}

	public List<Factura> listarFacturasPorVendedor(int idVendedor) {
		return controlFactura.listarFacturasPorVendedor(idVendedor);
	}
}
