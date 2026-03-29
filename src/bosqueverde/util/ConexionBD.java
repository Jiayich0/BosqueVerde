package bosqueverde.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Const.DB_PATH);
	}

	public static void inicializarBD() {
		String sql = """
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

		try (Connection conn = getConnection();
			 Statement stmt = conn.createStatement()) {

			stmt.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}