package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.model.Cliente;
import bosquedeletras.model.Vendedor;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;
	private ControlCliente controlCliente;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
		this.controlLibro = new ControlLibro();
		this.controlCliente = new ControlCliente();
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

	// -------------------- Subsistema Cliente -------------------------

	public boolean altaCliente(String nombre, String primerApellido, String segundoApellido, String dni,
			String email) {
		return controlCliente.altaCliente(nombre, primerApellido, segundoApellido, dni, email);
	}

	public boolean bajaCliente(String dni) {
		return controlCliente.bajaCliente(dni);
	}

	public boolean modificarCliente(String nombre, String primerApellido, String segundoApellido, String dni,
			String email) {
		return controlCliente.modificarCliente(nombre, primerApellido, segundoApellido, dni, email);
	}

	public Cliente leerCliente(String dni) {
		return controlCliente.leerCliente(dni);
	}

	public List<Cliente> listarClientes() {
		return controlCliente.listarClientes();
	}

	public boolean registrarSocio(String numeroSocio, String fechaInscripcion, String dniCliente) {
		return controlCliente.registrarSocio(numeroSocio, fechaInscripcion, dniCliente);
	}

	public boolean darBajaSocio(String dniCliente) {
		return controlCliente.darBajaSocio(dniCliente);
	}

	public bosquedeletras.model.Socio leerSocio(String dniCliente) {
		return controlCliente.leerSocio(dniCliente);
	}
}
