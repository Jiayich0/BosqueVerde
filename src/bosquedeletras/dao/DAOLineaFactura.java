package bosquedeletras.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bosquedeletras.model.LineaFactura;
import bosquedeletras.utils.ConexionBD;

public class DAOLineaFactura {

	public boolean insertar(LineaFactura lf) {
		String sql = "INSERT INTO linea_factura (id_factura, id_libro, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, lf.getIdFactura());
			ps.setInt(2, lf.getIdLibro());
			ps.setInt(3, lf.getCantidad());
			ps.setDouble(4, lf.getPrecioUnitario());
			ps.setDouble(5, lf.getSubtotal());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizar(LineaFactura lf) {
		String sql = "UPDATE linea_factura SET id_factura = ?, id_libro = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, lf.getIdFactura());
			ps.setInt(2, lf.getIdLibro());
			ps.setInt(3, lf.getCantidad());
			ps.setDouble(4, lf.getPrecioUnitario());
			ps.setDouble(5, lf.getSubtotal());
			ps.setInt(6, lf.getId());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public LineaFactura buscarPorId(int id) {
		String sql = "SELECT * FROM linea_factura WHERE id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapearLineaFactura(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<LineaFactura> listar() {
		List<LineaFactura> lineas = new ArrayList<>();
		String sql = "SELECT * FROM linea_factura";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				lineas.add(mapearLineaFactura(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lineas;
	}

	public List<LineaFactura> listarPorFactura(int idFactura) {
		List<LineaFactura> lineas = new ArrayList<>();
		String sql = "SELECT * FROM linea_factura WHERE id_factura = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idFactura);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lineas.add(mapearLineaFactura(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lineas;
	}

	private LineaFactura mapearLineaFactura(ResultSet rs) throws SQLException {
		return new LineaFactura(
				rs.getInt("id"),
				rs.getInt("id_factura"),
				rs.getInt("id_libro"),
				rs.getInt("cantidad"),
				rs.getDouble("precio_unitario"),
				rs.getDouble("subtotal"));
	}
}
