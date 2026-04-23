package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.Cliente;
import bosquedeletras.utils.ConexionBD;

public class DAOCliente {

	public boolean insertar(Cliente c) {
		String sql = "INSERT INTO cliente (nombre, primer_apellido, segundo_apellido, dni, email, activo) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, c.getNombre());
			ps.setString(2, c.getPrimerApellido());
			ps.setString(3, c.getSegundoApellido());
			ps.setString(4, c.getDni());
			ps.setString(5, c.getEmail());
			ps.setInt(6, c.isActivo() ? 1 : 0);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizar(Cliente c) {
		String sql = "UPDATE cliente SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, email = ? WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, c.getNombre());
			ps.setString(2, c.getPrimerApellido());
			ps.setString(3, c.getSegundoApellido());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getDni());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean bajaLogica(String dni) {
		String sql = "UPDATE cliente SET activo = 0 WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean reactivar(String dni) {
		String sql = "UPDATE cliente SET activo = 1 WHERE dni = ? AND activo = 0";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cliente buscarPorId(int id) {
	String sql = "SELECT * FROM cliente WHERE id = ? AND activo = 1";

	try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
		ps.setInt(1, id);

		try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return mapearCliente(rs);
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return null;
}

	public Cliente buscarPorDni(String dni) {
		String sql = "SELECT * FROM cliente WHERE dni = ? AND activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearCliente(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Cliente buscarPorDniTotal(String dni) {
		String sql = "SELECT * FROM cliente WHERE dni = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dni);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearCliente(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente WHERE activo = 1";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				clientes.add(mapearCliente(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

	private Cliente mapearCliente(ResultSet rs) throws SQLException {
		return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("primer_apellido"),
				rs.getString("segundo_apellido"), rs.getString("dni"), rs.getString("email"), rs.getInt("activo") == 1);
	}
}
