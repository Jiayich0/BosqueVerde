package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.Categoria;
import bosquedeletras.utils.ConexionBD;

/*esta implementacion presupone que en la BD
la tabla se llama categoria y tiene columnas:

- id
- nombre
- descripcion
- activo
*/
public class DAOCategoria {

	public boolean insertar(Categoria categoria) {
		String sql = "INSERT INTO categoria (nombre, descripcion, activo) VALUES (?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, categoria.getNombre());
			ps.setString(2, categoria.getDescripcion());
			ps.setInt(3, categoria.isActivo() ? 1 : 0);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizar(Categoria categoria) {
		String sql = "UPDATE categoria SET descripcion = ? WHERE nombre = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, categoria.getDescripcion());
			ps.setString(2, categoria.getNombre());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean bajaLogica(String nombre) {
		String sql = "UPDATE categoria SET activo = 0 WHERE nombre = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nombre);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean reactivar(String nombre) {
		String sql = "UPDATE categoria SET activo = 1 WHERE nombre = ? AND activo = 0";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nombre);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Categoria buscarPorNombre(String nombre) {
		String sql = "SELECT * FROM categoria WHERE nombre = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nombre);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearCategoria(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Categoria buscarPorNombreTotal(String nombre) {
		String sql = "SELECT * FROM categoria WHERE nombre = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nombre);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearCategoria(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE activo = 1";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				categorias.add(mapearCategoria(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	private Categoria mapearCategoria(ResultSet rs) throws SQLException {
		return new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
				rs.getInt("activo") == 1);
	}
}