package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.model.Vendedor;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;

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

	public boolean altaVendedor(String nombre, String primerApellido, String segundoApellido, String dni,
			String telefono) {
		return controlVendedor.altaVendedor(nombre, primerApellido, segundoApellido, dni, telefono);
	}

	public boolean bajaVendedor(String dni) {
		return controlVendedor.bajaVendedor(dni);
	}

	public boolean modificarVendedor(String nombre, String primerApellido, String segundoApellido, String dni,
			String telefono) {
		return controlVendedor.modificarVendedor(nombre, primerApellido, segundoApellido, dni, telefono);
	}

	public Vendedor leerVendedor(String dni) {
		return controlVendedor.leerVendedor(dni);
	}

	public List<Vendedor> listarVendedores() {
		return controlVendedor.listarVendedores();
	}

	public boolean altaLibro(String titulo, String autor, String isbn, String editorial, int ano) {
		return controlLibro.altaLibro(titulo, autor, isbn, editorial, ano);
	}

	public boolean bajaLibro(String isbn) {
		return controlLibro.bajaLibro(isbn);
	}

	public boolean modificarLibro(String titulo, String autor, String isbn, String editorial, int ano) {
		return controlLibro.modificarLibro(titulo, autor, isbn, editorial, ano);
	}

	public bosquedeletras.model.Libro leerLibro(String isbn) {
		return controlLibro.leerLibro(isbn);
	}

	public java.util.List<bosquedeletras.model.Libro> listarLibros() {
		return controlLibro.listarLibros();
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
