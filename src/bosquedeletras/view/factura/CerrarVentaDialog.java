package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;

public class CerrarVentaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtIdFactura;

	public CerrarVentaDialog(Frame parent) {
		super(parent, "CERRAR VENTA", true);
		setSize(400, 180);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField(15);
		panelCentro.add(txtIdFactura);

		add(panelCentro, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnCerrar = new JButton("Cerrar venta");
		JButton btnCancelar = new JButton("Cancelar");

		btnCerrar.addActionListener(e -> cerrarVenta());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnCerrar);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void cerrarVenta() {
		try {
			int idFactura = Integer.parseInt(txtIdFactura.getText().trim());

			Factura factura = SistemaBDL.getInstance().leerFactura(idFactura);
			if (factura == null) {
				JOptionPane.showMessageDialog(this, "No existe una factura con ese ID.",
						"NO EXISTE - CERRAR VENTA", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int confirmacion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres cerrar la venta con ID " + idFactura + "?",
					"CONFIRMAR CIERRE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (confirmacion != JOptionPane.YES_OPTION) {
				return;
			}

			boolean cierreCorrecto = SistemaBDL.getInstance().cerrarVenta(idFactura);

			if (cierreCorrecto) {
				JOptionPane.showMessageDialog(this, "Venta cerrada correctamente.", "CIERRE CORRECTO",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se ha podido cerrar la venta.", "ERROR CIERRE",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
