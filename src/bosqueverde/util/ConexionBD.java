package bosqueverde.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Const.DB_PATH);
	}
}