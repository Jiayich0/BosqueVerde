package bosquedeletras.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bosquedeletras.model.Vendedor;
import bosquedeletras.utils.Utils;

public class LeerVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerVendedorDialog(Dialog parent, Vendedor v) {
		super(parent, "LEER VENDEDOR", true);
		setSize(700, 350);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(v);
	}

	private void initComponents(Vendedor v) {
		setLayout(new BorderLayout(15, 15));

		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 20));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel panelImagen = crearPanelImagen();
		JPanel panelDatos = crearPanelDatos(v);

		panelCentral.add(panelImagen);
		panelCentral.add(panelDatos);

		add(panelCentral, BorderLayout.CENTER);

		JPanel panelBoton = new JPanel();
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> dispose());
		panelBoton.add(btnOk);

		add(panelBoton, BorderLayout.SOUTH);
	}

	private JPanel crearPanelImagen() {
		JPanel panel = new JPanel(new BorderLayout());

		JLabel lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(JLabel.CENTER);

		ImageIcon icon = new ImageIcon(Utils.VENDEDOR_IMG);
		Image imagenEscalada = icon.getImage().getScaledInstance(-1, 260, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(imagenEscalada));

		panel.add(lblImagen, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelDatos(Vendedor v) {
		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 12));

		panel.add(new JLabel("ID:"));
		panel.add(new JLabel(String.valueOf(v.getId())));

		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(v.getNombre()));

		panel.add(new JLabel("Primer apellido:"));
		panel.add(new JLabel(v.getPrimerApellido()));

		panel.add(new JLabel("Segundo apellido:"));
		panel.add(new JLabel(v.getSegundoApellido() != null ? v.getSegundoApellido() : ""));

		panel.add(new JLabel("DNI:"));
		panel.add(new JLabel(v.getDni()));

		panel.add(new JLabel("Teléfono:"));
		panel.add(new JLabel(v.getTelefono()));

		return panel;
	}
}