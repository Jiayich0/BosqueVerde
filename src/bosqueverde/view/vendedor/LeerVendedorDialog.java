package bosqueverde.view.vendedor;

import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import bosqueverde.model.Vendedor;

public class LeerVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerVendedorDialog(Dialog parent, Vendedor v) {
		super(parent, "LEER VENDEDOR", true);
		setSize(400, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(v);
	}

	private void initComponents(Vendedor v) {
		setLayout(new GridLayout(7, 2, 10, 10));
		
		add(new JLabel("ID:"));
		add(new JLabel(String.valueOf(v.getId())));

		add(new JLabel("Nombre:"));
		add(new JLabel(v.getNombre()));

		add(new JLabel("Primer apellido:"));
		add(new JLabel(v.getPrimerApellido()));

		add(new JLabel("Segundo apellido:"));
		add(new JLabel(v.getSegundoApellido() != null ? v.getSegundoApellido() : ""));

		add(new JLabel("DNI:"));
		add(new JLabel(v.getDni()));

		add(new JLabel("Teléfono:"));
		add(new JLabel(v.getTelefono()));

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> dispose());

		add(new JLabel());
		add(btnOk);
	}
}