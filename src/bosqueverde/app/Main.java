package bosqueverde.app;

import java.sql.Connection;

import bosqueverde.util.DatabaseConnection;

public class Main {

    public static void main(String[] args) {
    	
    	// Versión inicial de conexión. Pendiente de modificar según avance el proyecto
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Conexión correcta con SQLite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}