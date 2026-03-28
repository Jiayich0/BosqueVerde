package bosqueverde.facade;

import java.util.List;

import bosqueverde.model.Vendedor;

public class SistemaBV {

	private static SistemaBV instancia;
	private ControlVendedor controlVendedor;

	private SistemaBV() {
		this.controlVendedor = new ControlVendedor();
	}

	public static SistemaBV getInstance() {
		if (instancia == null) {
			instancia = new SistemaBV();
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
}
