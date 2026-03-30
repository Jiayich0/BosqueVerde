package bosqueverde.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Utils.DB_PATH);
	}

	public static void inicializarBD() {
		String sqlTablaVendedor = """
					CREATE TABLE IF NOT EXISTS vendedor (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						nombre TEXT NOT NULL,
						primer_apellido TEXT NOT NULL,
						segundo_apellido TEXT,
						dni TEXT UNIQUE NOT NULL,
						telefono TEXT,
						activo INTEGER NOT NULL
					);
				""";

		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

			stmt.execute(sqlTablaVendedor);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------
	// | SOLO DEPURACIÓN |
	// | (Revisar para no dejar permanentemente ninguna llamada en el código) |
	// --------------------------------------------------------------------------

	public static void vaciarVendedor() {
		String sqlDelete = "DELETE FROM vendedor";
		String sqlResetSequence = "DELETE FROM sqlite_sequence WHERE name = 'vendedor'";

		try (Connection conn = getConnection();
			 Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(sqlDelete);
			stmt.executeUpdate(sqlResetSequence);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}