package bosquedeletras.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new SQLException("SQLite JDBC driver not found", e);
		}
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

		String sqlTablaLibro = """
					CREATE TABLE IF NOT EXISTS libro (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						titulo TEXT NOT NULL,
						autor TEXT NOT NULL,
						isbn TEXT UNIQUE NOT NULL,
						editorial TEXT NOT NULL,
						ano INTEGER NOT NULL,
						activo INTEGER NOT NULL
					);
				""";

		String sqlTablaFactura = """
					CREATE TABLE IF NOT EXISTS factura (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						fecha TEXT NOT NULL,
						total REAL NOT NULL,
						cerrada INTEGER NOT NULL,
						id_cliente INTEGER NOT NULL,
						id_vendedor INTEGER NOT NULL
					);
				""";

		String sqlTablaLineaFactura = """
					CREATE TABLE IF NOT EXISTS linea_factura (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						id_factura INTEGER NOT NULL,
						id_libro INTEGER NOT NULL,
						cantidad INTEGER NOT NULL,
						precio_unitario REAL NOT NULL,
						subtotal REAL NOT NULL
					);
				""";

		String sqlTablaCategoria = """
					CREATE TABLE IF NOT EXISTS categoria (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						nombre TEXT UNIQUE NOT NULL,
						descripcion TEXT,
						activo INTEGER NOT NULL
					);
				""";

		String sqlTablaEditorial = """
				CREATE TABLE IF NOT EXISTS editorial (
							id INTEGER PRIMARY KEY AUTOINCREMENT,
				 			id_editorial TEXT UNIQUE NOT NULL,
				 			nombre TEXT NOT NULL,
				 			activo INTEGER NOT NULL

				);
				 	""";

		String sqlTablaCliente = """
					CREATE TABLE IF NOT EXISTS cliente (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						nombre TEXT NOT NULL,
						primer_apellido TEXT NOT NULL,
						segundo_apellido TEXT,
						dni TEXT UNIQUE NOT NULL,
						email TEXT,
						activo INTEGER NOT NULL
					);
				""";

		String sqlTablaSocio = """
					CREATE TABLE IF NOT EXISTS socio (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						numero_socio TEXT UNIQUE NOT NULL,
						fecha_inscripcion TEXT NOT NULL,
						socio_activo INTEGER NOT NULL,
						dni_cliente TEXT NOT NULL,
						FOREIGN KEY (dni_cliente) REFERENCES cliente(dni)
					);
				""";

		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

			stmt.execute(sqlTablaVendedor);
			stmt.execute(sqlTablaLibro);
			stmt.execute(sqlTablaFactura);
			stmt.execute(sqlTablaLineaFactura);
			stmt.execute(sqlTablaCategoria);
			stmt.execute(sqlTablaEditorial);
			stmt.execute(sqlTablaCliente);
			stmt.execute(sqlTablaSocio);

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

		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(sqlDelete);
			stmt.executeUpdate(sqlResetSequence);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
