package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.model.Vendedor;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
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
}
