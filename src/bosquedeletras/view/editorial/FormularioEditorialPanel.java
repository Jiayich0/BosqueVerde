package bosquedeletras.view.editorial;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioEditorialPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private JTextField txtId;

	public FormularioEditorialPanel() {
		setLayout(new GridLayout(5, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("ID Editorial:"));
		txtId = new JTextField();
		add(txtId);

	}

	public String getNombre() {
		return txtNombre.getText().trim();
	}

	public String getId() {
		return txtId.getText().trim().toUpperCase();
	}

	public void setNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public void setId(String dni) {
		txtId.setText(dni);
	}

	public void setIdEditable(boolean editable) {
		txtId.setEditable(editable);
	}
}