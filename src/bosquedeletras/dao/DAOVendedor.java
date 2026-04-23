package bosquedeletras.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.Vendedor;
import bosquedeletras.utils.ConexionBD;

public class DAOVendedor {

	public boolean insertar(Vendedor v) {
		String sql = "INSERT INTO vendedor (nombre, primer_apellido, segundo_apellido, dni, telefono, activo) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, v.getNombre());
			ps.setString(2, v.getPrimerApellido());
			ps.setString(3, v.getSegundoApellido());
			ps.setString(4, v.getDni());
			ps.setString(5, v.getTelefono());
			ps.setInt(6, v.isActivo() ? 1 : 0);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizar(Vendedor v) {
		String sql = "UPDATE vendedor SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ? WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, v.getNombre());
			ps.setString(2, v.getPrimerApellido());
			ps.setString(3, v.getSegundoApellido());
			ps.setString(4, v.getTelefono());
			ps.setString(5, v.getDni());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean bajaLogica(String dni) {
		String sql = "UPDATE vendedor SET activo = 0 WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean reactivar(String dni) {
		String sql = "UPDATE vendedor SET activo = 1 WHERE dni = ? AND activo = 0";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Vendedor buscarPorDni(String dni) {
		String sql = "SELECT * FROM vendedor WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearVendedor(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Vendedor buscarPorDniTotal(String dni) {
		String sql = "SELECT * FROM vendedor WHERE dni = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearVendedor(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Vendedor> listar() {
		List<Vendedor> vendedores = new ArrayList<>();
		String sql = "SELECT * FROM vendedor WHERE activo = 1";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				vendedores.add(mapearVendedor(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vendedores;
	}

	private Vendedor mapearVendedor(ResultSet rs) throws SQLException {
		return new Vendedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("primer_apellido"),
				rs.getString("segundo_apellido"), rs.getString("dni"), rs.getString("telefono"),
				rs.getInt("activo") == 1);
	}

	public Vendedor buscarPorId(int id) {
		String sql = "SELECT * FROM vendedor WHERE id = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearVendedor(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}