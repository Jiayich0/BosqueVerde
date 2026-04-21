package bosquedeletras.view.cliente;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioClientePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido;
	private JTextField txtDni;
	private JTextField txtEmail;

	public FormularioClientePanel() {
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

		add(new JLabel("Email:"));
		txtEmail = new JTextField();
		add(txtEmail);
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
		return txtDni.getText().trim().toUpperCase();
	}

	public String getEmail() {
		return txtEmail.getText().trim();
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

	public void setEmail(String email) {
		txtEmail.setText(email != null ? email : "");
	}

	public void setDniEditable(boolean editable) {
		txtDni.setEditable(editable);
	}
}
