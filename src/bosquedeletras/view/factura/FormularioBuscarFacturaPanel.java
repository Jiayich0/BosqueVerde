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

public class BajaVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDni;

	public BajaVendedorDialog(Frame parent) {
		super(parent, "BAJA VENDEDOR", true);
		setSize(400, 180);
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

		JButton btnDarBaja = new JButton("Dar de baja");
		JButton btnCancelar = new JButton("Cancelar");

		btnDarBaja.addActionListener(e -> darBaja());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnDarBaja);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void darBaja() {
		String dni = txtDni.getText().trim().toUpperCase();

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO - BAJA VENDEDOR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Vendedor vendedor = SistemaBDL.getInstance().leerVendedor(dni);

		if (vendedor == null) {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja. No existe un vendedor con ese DNI.",
					"NO EXISTENTE - BAJA VENDEDOR", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que quieres dar de baja al vendedor con DNI " + dni + "?", "CONFIRMAR BAJA",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		boolean bajaCorrecta = SistemaBDL.getInstance().bajaVendedor(dni);

		if (bajaCorrecta) {
			JOptionPane.showMessageDialog(this, "Vendedor dado de baja correctamente.", "BAJA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja al vendedor.", "ERROR BAJA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
