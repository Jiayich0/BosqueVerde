package bosquedeletras.model;

import bosquedeletras.strategy.Sortable;

public class Libro implements Sortable {

	private int id;
	private boolean activo;

	private String titulo;
	private String autor;
	private String isbn;
	private String editorial;
	private int ano;

	public Libro(String titulo, String autor, String isbn, String editorial, int ano) {
		this.activo = true;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.editorial = editorial;
		this.ano = ano;
	}

	public Libro(int id, String titulo, String autor, String isbn, String editorial, int ano, boolean activo) {
		this.id = id;
		this.activo = activo;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.editorial = editorial;
		this.ano = ano;
	}

	// Getters
	@Override
	public int getId() {
		return id;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public int getAno() {
		return ano;
	}

	// Setters

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return titulo + " - " + autor + " (" + isbn + ")";
	}
}