package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bosquedeletras.model.Socio;
import bosquedeletras.utils.ConexionBD;

public class DAOSocio {

	public boolean guardarSocio(Socio s) {
		String sql = "INSERT INTO socio (numero_socio, fecha_inscripcion, socio_activo, dni_cliente) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, s.getNumeroSocio());
			ps.setString(2, s.getFechaInscripcion());
			ps.setInt(3, s.isSocioActivo() ? 1 : 0);
			ps.setString(4, s.getDni());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean desactivarSocio(String dniCliente) {
		String sql = "UPDATE socio SET socio_activo = 0 WHERE dni_cliente = ? AND socio_activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dniCliente);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Socio buscarPorDniCliente(String dniCliente) {
		String sql = "SELECT * FROM socio WHERE dni_cliente = ? AND socio_activo = 1";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dniCliente);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearSocio(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean existeNumeroSocio(String numeroSocio) {
		String sql = "SELECT 1 FROM socio WHERE numero_socio = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, numeroSocio);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private Socio mapearSocio(ResultSet rs) throws SQLException {
		return new Socio(rs.getInt("id"), rs.getString("nombre"), rs.getString("primer_apellido"),
				rs.getString("segundo_apellido"), rs.getString("dni"), rs.getString("email"), rs.getInt("activo") == 1,
				rs.getString("numero_socio"), rs.getString("fecha_inscripcion"), rs.getInt("socio_activo") == 1);
	}
}
