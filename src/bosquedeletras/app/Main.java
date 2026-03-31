package bosquedeletras.app;

import javax.swing.SwingUtilities;

import bosquedeletras.utils.ConexionBD;
import bosquedeletras.view.MainWindow;

public class Main {

	public static void main(String[] args) {
		try {
			initDB();
			initGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initDB() throws Exception {

		/*
		 * Pruebas de conexión try (Connection conn = ConexionBD.getConnection()) {
		 * System.out.println("Conexión correcta con SQLite"); }
		 */
		ConexionBD.inicializarBD();
		System.out.println("Base de datos inicializada correctamente");
	}

	private static void initGUI() {
		SwingUtilities.invokeLater(() -> {
			MainWindow ventanaPrincipal = new MainWindow();
			ventanaPrincipal.setVisible(true);
		});
	}
}