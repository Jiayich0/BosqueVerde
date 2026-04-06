package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.dao.DAOLibro;
import bosquedeletras.model.Libro;

public class ControlLibro {

    private DAOLibro daoLibro;

    public ControlLibro() {
        this.daoLibro = new DAOLibro();
    }

    public boolean altaLibro(String titulo, String autor, String isbn, String editorial, int ano) {
        Libro libroExistente = daoLibro.buscarPorIsbnTotal(isbn);

        if (libroExistente == null) {
            Libro nuevoLibro = new Libro(titulo, autor, isbn, editorial, ano);
            return daoLibro.insertar(nuevoLibro);
        }

        if (libroExistente.isActivo()) {
            return false;
        }

        boolean reactivado = daoLibro.reactivar(isbn);
        if (!reactivado) {
            return false;
        }

        libroExistente.setTitulo(titulo);
        libroExistente.setAutor(autor);
        libroExistente.setEditorial(editorial);
        libroExistente.setAno(ano);

        return daoLibro.actualizar(libroExistente);
    }

    public boolean bajaLibro(String isbn) {
        return daoLibro.bajaLogica(isbn);
    }

    public boolean modificarLibro(String titulo, String autor, String isbn, String editorial, int ano) {
        Libro libro = daoLibro.buscarPorIsbn(isbn);

        if (libro == null) {
            return false;
        }

        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAno(ano);

        return daoLibro.actualizar(libro);
    }

    public Libro leerLibro(String isbn) {
        return daoLibro.buscarPorIsbn(isbn);
    }

    public List<Libro> listarLibros() {
        return daoLibro.listar();
    }
}