package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOCategoria;
import bosquedeletras.model.Categoria;

public class ControlCategoria {

    private DAOCategoria daoCategoria;

    public ControlCategoria() {
        this.daoCategoria = new DAOCategoria();
    }

    public boolean altaCategoria(String nombre, String descripcion) {
        Categoria categoriaExistente = daoCategoria.buscarPorNombreTotal(nombre);

        if (categoriaExistente == null) {
            Categoria nuevaCategoria = new Categoria(nombre, descripcion);
            return daoCategoria.insertar(nuevaCategoria);
        }

        if (categoriaExistente.isActivo()) {
            return false;
        }

        boolean reactivada = daoCategoria.reactivar(nombre);
        if (!reactivada) {
            return false;
        }

        categoriaExistente.setDescripcion(descripcion);

        return daoCategoria.actualizar(categoriaExistente);
    }

    public boolean bajaCategoria(String nombre) {
        return daoCategoria.bajaLogica(nombre);
    }

    public boolean modificarCategoria(String nombre, String descripcion) {
        Categoria categoria = daoCategoria.buscarPorNombre(nombre);

        if (categoria == null) {
            return false;
        }

        categoria.setDescripcion(descripcion);

        return daoCategoria.actualizar(categoria);
    }

    public Categoria leerCategoria(String nombre) {
        return daoCategoria.buscarPorNombre(nombre);
    }

    public List<Categoria> listarCategorias() {
        return daoCategoria.listar();
    }
}