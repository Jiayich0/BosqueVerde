package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOCliente;
import bosquedeletras.dao.DAOSocio;
import bosquedeletras.model.Cliente;
import bosquedeletras.model.Socio;

public class ControlCliente {

	private DAOCliente daoCliente;
	private DAOSocio daoSocio;

	public ControlCliente() {
		this.daoCliente = new DAOCliente();
		this.daoSocio = new DAOSocio();
	}

	// ===================== Alta cliente ==============================

	public boolean altaCliente(String nombre, String primerApellido, String segundoApellido, String dni,
			String email) {
		Cliente clienteExistente = daoCliente.buscarPorDniTotal(dni);

		if (clienteExistente == null) {
			Cliente nuevoCliente = new Cliente(nombre, primerApellido, segundoApellido, dni, email);
			return daoCliente.insertar(nuevoCliente);
		}

		if (clienteExistente.isActivo()) {
			return false;
		}

		boolean reactivado = daoCliente.reactivar(dni);
		if (!reactivado) {
			return false;
		}

		clienteExistente.setNombre(nombre);
		clienteExistente.setPrimerApellido(primerApellido);
		clienteExistente.setSegundoApellido(segundoApellido);
		clienteExistente.setEmail(email);

		return daoCliente.actualizar(clienteExistente);
	}

	// ===================== Baja cliente ==============================

	public boolean bajaCliente(String dni) {
		return daoCliente.bajaLogica(dni);
	}

	// ===================== Modificar cliente =========================

	public boolean modificarCliente(String nombre, String primerApellido, String segundoApellido, String dni,
			String email) {
		Cliente cliente = daoCliente.buscarPorDni(dni);

		if (cliente == null) {
			return false;
		}

		cliente.setNombre(nombre);
		cliente.setPrimerApellido(primerApellido);
		cliente.setSegundoApellido(segundoApellido);
		cliente.setEmail(email);

		return daoCliente.actualizar(cliente);
	}

	// ===================== Leer cliente ==============================

	public Cliente leerCliente(String dni) {
		return daoCliente.buscarPorDni(dni);
	}

	// ===================== Listar clientes ===========================

	public List<Cliente> listarClientes() {
		return daoCliente.listar();
	}

	// ===================== Registrar socio ===========================

	public boolean registrarSocio(String numeroSocio, String fechaInscripcion, String dniCliente) {
		Cliente cliente = daoCliente.buscarPorDni(dniCliente);
		if (cliente == null) {
			return false;
		}

		Socio socioExistente = daoSocio.buscarPorDniCliente(dniCliente);
		if (socioExistente != null) {
			return false;
		}

		if (daoSocio.existeNumeroSocio(numeroSocio)) {
			return false;
		}

		Socio nuevoSocio = new Socio(numeroSocio, fechaInscripcion, dniCliente);
		return daoSocio.guardarSocio(nuevoSocio);
	}

	// ===================== Dar baja socio ============================

	public boolean darBajaSocio(String dniCliente) {
		Socio socio = daoSocio.buscarPorDniCliente(dniCliente);

		if (socio == null) {
			return false;
		}

		return daoSocio.desactivarSocio(dniCliente);
	}

	public Socio leerSocio(String dniCliente) {
		return daoSocio.buscarPorDniCliente(dniCliente);
	}
}
