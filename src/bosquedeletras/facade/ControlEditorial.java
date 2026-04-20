package bosquedeletras.facade;

import bosquedeletras.dao.DAOEditorial;
import bosquedeletras.model.Editorial;
import java.util.List;

public class ControlEditorial {

    private DAOEditorial daoEditorial;

    public ControlEditorial() {
        this.daoEditorial = new DAOEditorial();
    }

    public int altaEditorial(String id, String nombre) {
        // Comprobar validez sintáctica
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return -1;  // Datos no válidos
        }

        id = id.trim().toUpperCase();
        nombre = nombre.trim();

        Editorial existente = daoEditorial.buscarPorId(id);

        // Caso 1: No existe → insertar nueva
        if (existente == null) {
            Editorial nueva = new Editorial(id, nombre);
            return daoEditorial.insertar(nueva) ? 1 : 0;
        }

        // Caso 2: Existe pero está inactiva → reactivar
        if (!existente.isActivo()) {
            boolean reactivado = daoEditorial.reactivar(id);
            if (reactivado) {
                return 2;
            }
            return 0;
        }

        // Caso 3: Existe y está activa → repetición
        return 0;
    }

    // Buscar editorial por ID (solo activas)
    public Editorial buscarEditorial(String id) {
        return daoEditorial.buscarPorIdActivo(id);  // ← usa daoEditorial, no DAOEditorial
    }

    // Verificar si tiene libros asociados
    public boolean tieneLibrosAsociados(String id) {
        return daoEditorial.tieneLibrosAsociados(id);  // ← usa daoEditorial
    }

    // Baja lógica de editorial
    public boolean bajaEditorial(String id) {
        Editorial editorial = daoEditorial.buscarPorId(id);  // ← usa daoEditorial
        
        if (editorial == null) {
            return false;
        }
        
        if (!editorial.isActivo()) {
            return false;
        }
        
        if (tieneLibrosAsociados(id)) {
            return false;
        }
        
        return daoEditorial.bajaLogica(id); 
    }

    // Modificar editorial
public boolean modificarEditorial(String id, String nombre) {
    Editorial editorial = daoEditorial.buscarPorId(id);
    
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