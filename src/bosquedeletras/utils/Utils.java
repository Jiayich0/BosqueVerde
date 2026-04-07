package bosquedeletras.utils;

public class Utils {

	public static final String DB_PATH = "jdbc:sqlite:src/resources/database/bosqueDeLetras.db";
	public static final String BDL_IMG = "src/resources/icons/bosqueDeLetras.png";
	public static final String VENDEDOR_IMG = "src/resources/icons/vendedor.png";
	public static final String FACTURA_IMG = "src/resources/icons/factura icono.png";

	public static boolean isValidDni(String dni) {

		if (dni == null)
			return false;

		dni = dni.trim();

		if (dni.isEmpty())
			return false;
		if (dni.length() != 9)
			return false;

		for (int i = 0; i < dni.length(); i++) {
			if (!Character.isLetterOrDigit(dni.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidTelefono(String telefono) {

		if (telefono == null)
			return false;

		telefono = telefono.trim();

		if (telefono.isEmpty())
			return false;

		for (int i = 0; i < telefono.length(); i++) {
			char c = telefono.charAt(i);

			if (!Character.isDigit(c) && c != '+' && c != ' ') {
				return false;
			}
		}
		return true;
	}

}
