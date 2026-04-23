package bosquedeletras.facade;

import bosquedeletras.dao.DAOEditorial;
import bosquedeletras.model.Editorial;
import java.util.List;

public class ControlEditorial {

	private DAOEditorial daoEditorial;

	public ControlEditorial() {
		this.daoEditorial = new DAOEditorial();
	}

	public int altaEditorial(String idStr, String nombre) {
		// Comprobar validez sintáctica
		if (idStr == null || idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
			return -1; // Datos no válidos
		}

		String id_editorial = idStr.trim().toUpperCase();
		nombre = nombre.trim();

		Editorial existente = daoEditorial.buscarPorIdEditorial(id_editorial);

		// Caso 1: No existe → insertar nueva
		if (existente == null) {
			Editorial nueva = new Editorial(id_editorial, nombre);
			return daoEditorial.insertar(nueva) ? 1 : 0;
		}

		// Caso 2: Existe pero está inactiva → reactivar
		if (!existente.isActivo()) {
			boolean reactivado = daoEditorial.reactivar(id_editorial);
			if (reactivado) {
				return 2;
			}
			return 0;
		}

		// Caso 3: Existe y está activa → repetición
		return 0;
	}

	// Buscar editorial por ID (solo activas)
	public Editorial buscarEditorial(String idStr) {
		String id_editorial = idStr.trim().toUpperCase();
		return daoEditorial.buscarPorIdEditorialActivo(id_editorial);
	}

	// Verificar si tiene libros asociados
	public boolean tieneLibrosAsociados(String idStr) {
		String id_editorial = idStr.trim().toUpperCase();
		return daoEditorial.tieneLibrosAsociados(id_editorial);
	}

	// Baja lógica de editorial
	public boolean bajaEditorial(String idStr) {
		String id_editorial = idStr.trim().toUpperCase();
		Editorial editorial = daoEditorial.buscarPorIdEditorial(id_editorial);

		if (editorial == null) {
			return false;
		}

		if (!editorial.isActivo()) {
			return false;
		}

		if (tieneLibrosAsociados(idStr)) {
			return false;
		}

		return daoEditorial.bajaLogica(id_editorial);
	}

	// Modificar editorial
	public boolean modificarEditorial(String idStr, String nombre) {
		String id_editorial = idStr.trim().toUpperCase();
		Editorial editorial = daoEditorial.buscarPorIdEditorial(id_editorial);

		if (editorial == null) {
			return false;
		}

		if (!editorial.isActivo()) {
			return false;
		}

		editorial.setNombre(nombre);
		return daoEditorial.actualizar(editorial);
	}

	public List<Editorial> listarEditoriales() {
		return daoEditorial.listar();
	}
}