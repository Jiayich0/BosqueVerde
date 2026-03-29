package bosqueverde.view.vendedor;

import java.awt.GridLayout;

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

		add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("Primer apellido:"));
		txtPrimerApellido = new JTextField();
		add(txtPrimerApellido);

		add(new JLabel("Segundo apellido (opcional):"));
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
}