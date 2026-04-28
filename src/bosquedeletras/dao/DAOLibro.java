package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.Libro;
import bosquedeletras.utils.ConexionBD;

public class DAOLibro {

	public boolean insertar(Libro libro) {
		String sql = "INSERT INTO libro (titulo, autor, isbn, editorial, ano, id_categoria, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getAutor());
			ps.setString(3, libro.getIsbn());
			ps.setString(4, libro.getEditorial());
			ps.setInt(5, libro.getAno());
			ps.setInt(6, libro.getIdCategoria());
			ps.setInt(7, libro.isActivo() ? 1 : 0);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizar(Libro libro) {
		String sql = "UPDATE libro SET titulo = ?, autor = ?, editorial = ?, ano = ? WHERE isbn = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getAutor());
			ps.setString(3, libro.getEditorial());
			ps.setInt(4, libro.getAno());
			ps.setString(5, libro.getIsbn());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean bajaLogica(String isbn) {
		String sql = "UPDATE libro SET activo = 0 WHERE isbn = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean reactivar(String isbn) {
		String sql = "UPDATE libro SET activo = 1 WHERE isbn = ? AND activo = 0";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Libro buscarPorIsbn(String isbn) {
		String sql = "SELECT * FROM libro WHERE isbn = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearLibro(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Libro buscarPorIsbnTotal(String isbn) {
		String sql = "SELECT * FROM libro WHERE isbn = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearLibro(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Libro> listar() {
		List<Libro> libros = new ArrayList<>();
		String sql = "SELECT * FROM libro WHERE activo = 1";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				libros.add(mapearLibro(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return libros;
	}

	private Libro mapearLibro(ResultSet rs) throws SQLException {
		return new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"),
				rs.getString("editorial"), rs.getInt("ano"), rs.getInt("id_categoria"), rs.getInt("activo") == 1);
	}

	public Libro buscarPorId(int id) {
		String sql = "SELECT * FROM libro WHERE id = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearLibro(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean actualizarCategoria(String isbn, int idCategoria) {
		String sql = "UPDATE libro SET id_categoria = ? WHERE isbn = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idCategoria);
			ps.setString(2, isbn);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}