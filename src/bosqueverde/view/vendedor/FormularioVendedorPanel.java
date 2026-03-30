package bosqueverde.view.vendedor;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioVendedorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido;
	private JTextField txtDni;
	private JTextField txtTelefono;

	public FormularioVendedorPanel() {
		setLayout(new GridLayout(5, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("Primer apellido:"));
		txtPrimerApellido = new JTextField();
		add(txtPrimerApellido);

		add(new JLabel("Segundo apellido:"));
		txtSegundoApellido = new JTextField();
		add(txtSegundoApellido);

		add(new JLabel("DNI:"));
		txtDni = new JTextField();
		add(txtDni);

		add(new JLabel("Teléfono:"));
		txtTelefono = new JTextField();
		add(txtTelefono);
	}

	public String getNombre() {
		return txtNombre.getText().trim();
	}

	public String getPrimerApellido() {
		return txtPrimerApellido.getText().trim();
	}

	public String getSegundoApellido() {
		return txtSegundoApellido.getText().trim();
	}

	public String getDni() {
		return txtDni.getText().trim();
	}

	public String getTelefono() {
		return txtTelefono.getText().trim();
	}

	public void limpiar() {
		txtNombre.setText("");
		txtPrimerApellido.setText("");
		txtSegundoApellido.setText("");
		txtDni.setText("");
		txtTelefono.setText("");
	}

	public void setNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public void setPrimerApellido(String primerApellido) {
		txtPrimerApellido.setText(primerApellido);
	}

	public void setSegundoApellido(String segundoApellido) {
		txtSegundoApellido.setText(segundoApellido != null ? segundoApellido : "");
	}

	public void setDni(String dni) {
		txtDni.setText(dni);
	}

	public void setTelefono(String telefono) {
		txtTelefono.setText(telefono);
	}

	public void setDniEditable(boolean editable) {
		txtDni.setEditable(editable);
	}
}