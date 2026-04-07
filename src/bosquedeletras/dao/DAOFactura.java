package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.Factura;
import bosquedeletras.utils.ConexionBD;

public class DAOFactura {

	public int insertar(Factura f) {
		String sql = "INSERT INTO factura (fecha, total, cerrada, id_cliente, id_vendedor) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, f.getFecha());
			ps.setDouble(2, f.getTotal());
			ps.setInt(3, f.isCerrada() ? 1 : 0);
			ps.setInt(4, f.getIdCliente());
			ps.setInt(5, f.getIdVendedor());

			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						return rs.getInt(1);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public boolean actualizar(Factura f) {
		String sql = "UPDATE factura SET fecha = ?, total = ?, cerrada = ?, id_cliente = ?, id_vendedor = ? WHERE id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, f.getFecha());
			ps.setDouble(2, f.getTotal());
			ps.setInt(3, f.isCerrada() ? 1 : 0);
			ps.setInt(4, f.getIdCliente());
			ps.setInt(5, f.getIdVendedor());
			ps.setInt(6, f.getId());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Factura buscarPorId(int id) {
		String sql = "SELECT * FROM factura WHERE id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearFactura(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Factura> listar() {
		List<Factura> facturas = new ArrayList<>();
		String sql = "SELECT * FROM factura";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				facturas.add(mapearFactura(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return facturas;
	}

	public List<Factura> listarPorCliente(int idCliente) {
		List<Factura> facturas = new ArrayList<>();
		String sql = "SELECT * FROM factura WHERE id_cliente = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idCliente);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					facturas.add(mapearFactura(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return facturas;
	}

	public List<Factura> listarPorVendedor(int idVendedor) {
		List<Factura> facturas = new ArrayList<>();
		String sql = "SELECT * FROM factura WHERE id_vendedor = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idVendedor);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					facturas.add(mapearFactura(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return facturas;
	}

	private Factura mapearFactura(ResultSet rs) throws SQLException {
		return new Factura(
				rs.getInt("id"),
				rs.getString("fecha"),
				rs.getDouble("total"),
				rs.getInt("cerrada") == 1,
				rs.getInt("id_cliente"),
				rs.getInt("id_vendedor"));
	}
}
