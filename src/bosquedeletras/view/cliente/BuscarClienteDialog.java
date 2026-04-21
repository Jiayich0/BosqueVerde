package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Cliente;
import bosquedeletras.utils.Utils;

public class BuscarClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDni;
	private String modo; // "leer" | "modificar"

	public BuscarClienteDialog(Frame parent, String modo) {
		super(parent, "BUSCAR CLIENTE", true);
		this.modo = modo;
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
		String dni = txtDni.getText().trim().toUpperCase();

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Cliente cliente = SistemaBDL.getInstance().getControlCliente().leerCliente(dni);

		if (cliente == null) {
			JOptionPane.showMessageDialog(this, "El cliente indicado no existe.", "NO EXISTE",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ("leer".equals(modo)) {
			LeerClienteDialog dialog = new LeerClienteDialog(this, cliente);
			dialog.setVisible(true);
		} else {
			ModificarClienteDialog dialog = new ModificarClienteDialog(this, cliente);
			dialog.setVisible(true);
		}

		dispose();
	}
}
