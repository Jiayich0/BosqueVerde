package bosquedeletras.view.editorial;

import bosquedeletras.model.Editorial;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeerEditorialDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerEditorialDialog(Dialog parent, Editorial e) {
		super(parent, "LEER EDITORIAL", true);
		setSize(500, 250);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(e);
	}

	private void initComponents(Editorial e) {
		setLayout(new BorderLayout(15, 15));

		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel panelDatos = crearPanelDatos(e);
		panelCentral.add(panelDatos, BorderLayout.CENTER);

		add(panelCentral, BorderLayout.CENTER);

		JPanel panelBoton = new JPanel();
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e1 -> dispose());
		panelBoton.add(btnOk);

		add(panelBoton, BorderLayout.SOUTH);
	}

	private JPanel crearPanelDatos(Editorial e) {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 12));

		panel.add(new JLabel("ID Editorial:"));
		panel.add(new JLabel(e.getIdEditorial()));

		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(e.getNombre()));

		panel.add(new JLabel("Activo:"));
		panel.add(new JLabel(e.isActivo() ? "Sí" : "No"));

		return panel;
	}
}