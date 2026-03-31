package bosquedeletras.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Vendedor;
import bosquedeletras.utils.Utils;

public class BuscarVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDni;
	private String name;

	public BuscarVendedorDialog(Frame parent, String name) {
		super(parent, "BUSCAR VENDEDOR", true);
		this.name = name;
		setSize(350, 150);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("DNI:"));
		txtDni = new JTextField(15);
		panelCentro.add(txtDni);

		add(panelCentro, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnBuscar = new JButton("Buscar");
		JButton btnCancelar = new JButton("Cancelar");

		btnBuscar.addActionListener(e -> buscar());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnBuscar);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void buscar() {
		String dni = txtDni.getText().trim();

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Vendedor vendedor = SistemaBDL.getInstance().leerVendedor(dni);

		if (vendedor == null) {
			JOptionPane.showMessageDialog(this, "El vendedor indicado no existe.", "NO EXISTE",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ("leer".equals(name)) {
			LeerVendedorDialog dialog = new LeerVendedorDialog(this, vendedor);
			dialog.setVisible(true);
		} else {
			ModificarVendedorDialog dialog = new ModificarVendedorDialog(this, vendedor);
			dialog.setVisible(true);
		}

		dispose();
	}
}