package bosqueverde.utils;

public class Utils {
	
	public static final String DB_PATH = "jdbc:sqlite:src/resources/database/bosqueverde.db";
	
	public static boolean isValidDni(String dni) {
		
		if (dni == null) return false;

		dni = dni.trim();
		
		if(dni.isEmpty()) return false;
		if (dni.length() != 9) return false;
		
		for (int i = 0; i < dni.length(); i++) {
            if (!Character.isLetterOrDigit(dni.charAt(i))) {
                return false;
            }
        }
        return true;
	}
	
	public static boolean isValidTelefono(String telefono) {
		
		if (telefono == null) return false;
		
		telefono = telefono.trim();
		
		if (telefono.isEmpty()) return false;
		
		for (int i = 0; i < telefono.length(); i++) {
			char c = telefono.charAt(i);
			
			if (!Character.isDigit(c) && c != '+') {
				return false;
			}
		}
		return true;
	}
	
}