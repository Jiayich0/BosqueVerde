package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOVendedor;
import bosquedeletras.model.Vendedor;
import bosquedeletras.strategy.SortStrategy;

public class ControlVendedor {

	private DAOVendedor daoVendedor;

	public ControlVendedor() {
		this.daoVendedor = new DAOVendedor();
	}

	public boolean altaVendedor(String nombre, String primerApellido, String segundoApellido, String dni,
			String telefono) {
		Vendedor vendedorExistente = daoVendedor.buscarPorDniTotal(dni);

		if (vendedorExistente == null) {
			Vendedor nuevoVendedor = new Vendedor(nombre, primerApellido, segundoApellido, dni, telefono);
			return daoVendedor.insertar(nuevoVendedor);
		}

		if (vendedorExistente.isActivo()) {
			return false;
		}

		boolean reactivado = daoVendedor.reactivar(dni);
		if (!reactivado) {
			return false;
		}

		vendedorExistente.setNombre(nombre);
		vendedorExistente.setPrimerApellido(primerApellido);
		vendedorExistente.setSegundoApellido(segundoApellido);
		vendedorExistente.setTelefono(telefono);

		return daoVendedor.actualizar(vendedorExistente);
	}

	public boolean bajaVendedor(String dni) {
		return daoVendedor.bajaLogica(dni);
	}

	public boolean modificarVendedor(String nombre, String primerApellido, String segundoApellido, String dni,
			String telefono) {
		Vendedor vendedor = daoVendedor.buscarPorDni(dni);

		if (vendedor == null) {
			return false;
		}

		vendedor.setNombre(nombre);
		vendedor.setPrimerApellido(primerApellido);
		vendedor.setSegundoApellido(segundoApellido);
		vendedor.setTelefono(telefono);

		return daoVendedor.actualizar(vendedor);
	}

	public Vendedor leerVendedor(String dni) {
		return daoVendedor.buscarPorDni(dni);
	}

	public List<Vendedor> listarVendedores(SortStrategy<Vendedor> strategy) {
		List<Vendedor> lista = daoVendedor.listar();
		
		if (strategy != null) {
			strategy.sort(lista);
		}
		
		return lista;
	}
}