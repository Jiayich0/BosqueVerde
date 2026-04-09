package bosquedeletras.view.categoria;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bosquedeletras.model.Categoria;

public class LeerCategoriaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerCategoriaDialog(Dialog parent, Categoria c) {
		super(parent, "LEER CATEGORIA", true);
		setSize(500, 250);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(c);
	}

	private void initComponents(Categoria c) {
		setLayout(new BorderLayout(15, 15));

		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel panelDatos = crearPanelDatos(c);
		panelCentral.add(panelDatos, BorderLayout.CENTER);

		add(panelCentral, BorderLayout.CENTER);

		JPanel panelBoton = new JPanel();
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> dispose());
		panelBoton.add(btnOk);

		add(panelBoton, BorderLayout.SOUTH);
	}

	private JPanel crearPanelDatos(Categoria c) {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 12));

		panel.add(new JLabel("ID:"));
		panel.add(new JLabel(String.valueOf(c.getId())));

		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(c.getNombre()));

		panel.add(new JLabel("Descripción:"));
		panel.add(new JLabel(c.getDescripcion() != null && !c.getDescripcion().isEmpty() ? c.getDescripcion() : "-"));

		return panel;
	}
}