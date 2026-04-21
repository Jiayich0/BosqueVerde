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
import bosquedeletras.model.Socio;
import bosquedeletras.utils.Utils;

public class DarBajaSocioDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDniCliente;

	public DarBajaSocioDialog(Frame parent) {
		super(parent, "BAJA SOCIO", true);
		setSize(400, 180);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("DNI del cliente:"));
		txtDniCliente = new JTextField(15);
		panelCentro.add(txtDniCliente);
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
		String dniCliente = txtDniCliente.getText().trim().toUpperCase();

		if (!Utils.isValidDni(dniCliente)) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.",
					"INCORRECTO - BAJA SOCIO", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Socio socio = SistemaBDL.getInstance().leerSocio(dniCliente);

		if (socio == null) {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de baja. No existe un socio activo para ese cliente.",
					"NO EXISTENTE - BAJA SOCIO", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que quieres dar de baja al socio " + socio.getNumeroSocio() + "?", "CONFIRMAR BAJA",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		boolean bajaCorrecta = SistemaBDL.getInstance().darBajaSocio(dniCliente);

		if (bajaCorrecta) {
			JOptionPane.showMessageDialog(this, "Socio dado de baja correctamente.", "BAJA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja al socio.", "ERROR BAJA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
