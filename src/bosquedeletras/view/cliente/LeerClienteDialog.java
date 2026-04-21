package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bosquedeletras.model.Cliente;

public class LeerClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerClienteDialog(Dialog parent, Cliente c) {
		super(parent, "LEER CLIENTE", true);
		setSize(450, 280);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(c);
	}

	private void initComponents(Cliente c) {
		setLayout(new BorderLayout(15, 15));

		JPanel panelDatos = crearPanelDatos(c);
		panelDatos.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		add(panelDatos, BorderLayout.CENTER);

		JPanel panelBoton = new JPanel();
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> dispose());
		panelBoton.add(btnOk);
		add(panelBoton, BorderLayout.SOUTH);
	}

	private JPanel crearPanelDatos(Cliente c) {
		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 12));

		panel.add(new JLabel("ID:"));
		panel.add(new JLabel(String.valueOf(c.getId())));

		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(c.getNombre()));

		panel.add(new JLabel("Primer apellido:"));
		panel.add(new JLabel(c.getPrimerApellido()));

		panel.add(new JLabel("Segundo apellido:"));
		panel.add(new JLabel(c.getSegundoApellido() != null ? c.getSegundoApellido() : "-"));

		panel.add(new JLabel("DNI:"));
		panel.add(new JLabel(c.getDni()));

		panel.add(new JLabel("Email:"));
		panel.add(new JLabel(c.getEmail() != null ? c.getEmail() : "-"));

		return panel;
	}
}
