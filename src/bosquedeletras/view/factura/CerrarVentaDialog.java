package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;

public class CerrarVentaDialog extends JDialog {

	private JTextField txtIdFactura;

	public CerrarVentaDialog(java.awt.Frame owner) {
		super(owner, "Cerrar venta", true);

		setSize(300, 150);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelCampos = new JPanel(new GridLayout(1, 2, 5, 5));
		panelCampos.add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField();
		panelCampos.add(txtIdFactura);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> cerrarVenta());

		add(panelCampos, BorderLayout.CENTER);
		add(btnAceptar, BorderLayout.SOUTH);
	}

	private void cerrarVenta() {
		try {
			int idFactura = Integer.parseInt(txtIdFactura.getText().trim());

			boolean ok = SistemaBDL.getInstance().cerrarVenta(idFactura);

			if (ok) {
				JOptionPane.showMessageDialog(this, "Venta cerrada correctamente.");
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo cerrar la venta.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
