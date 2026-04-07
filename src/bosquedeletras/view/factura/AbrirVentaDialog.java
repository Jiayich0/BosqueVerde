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

public class AbrirVentaDialog extends JDialog {

	private JTextField txtFecha;
	private JTextField txtIdCliente;
	private JTextField txtIdVendedor;

	public AbrirVentaDialog(java.awt.Frame owner) {
		super(owner, "Abrir venta", true);

		setSize(350, 220);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
		panelCampos.add(new JLabel("Fecha:"));
		txtFecha = new JTextField();
		panelCampos.add(txtFecha);

		panelCampos.add(new JLabel("ID Cliente:"));
		txtIdCliente = new JTextField();
		panelCampos.add(txtIdCliente);

		panelCampos.add(new JLabel("ID Vendedor:"));
		txtIdVendedor = new JTextField();
		panelCampos.add(txtIdVendedor);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> abrirVenta());

		add(panelCampos, BorderLayout.CENTER);
		add(btnAceptar, BorderLayout.SOUTH);
	}

	private void abrirVenta() {
		try {
			String fecha = txtFecha.getText().trim();
			int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
			int idVendedor = Integer.parseInt(txtIdVendedor.getText().trim());

			int idFactura = SistemaBDL.getInstance().abrirVenta(fecha, idCliente, idVendedor);

			if (idFactura > 0) {
				JOptionPane.showMessageDialog(this, "Venta abierta correctamente. ID factura: " + idFactura);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo abrir la venta.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
