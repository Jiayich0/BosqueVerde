package bosqueverde.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosqueverde.facade.SistemaBV;
import bosqueverde.model.Vendedor;
import bosqueverde.utils.Utils;

public class BuscarVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDni;

	public BuscarVendedorDialog(Frame parent) {
		super(parent, "BUSCAR VENDEDOR", true);
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
		String dni = txtDni.getText();

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "Datos incorrectos");
			return;
		}

		Vendedor v = SistemaBV.getInstance().leerVendedor(dni);

		if (v == null) {
			JOptionPane.showMessageDialog(this, "Vendedor no existe");
			return;
		}
		
		//dispose();
		LeerVendedorDialog dialog = new LeerVendedorDialog(this, v);
		dialog.setVisible(true);

		dispose();
	}
}