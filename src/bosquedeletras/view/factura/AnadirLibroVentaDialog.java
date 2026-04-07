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

public class AnadirLibroVentaDialog extends JDialog {

	private JTextField txtIdFactura;
	private JTextField txtIdLibro;
	private JTextField txtCantidad;
	private JTextField txtPrecio;

	public AnadirLibroVentaDialog(java.awt.Frame owner) {
		super(owner, "Añadir libro a venta", true);

		setSize(350, 250);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

		panelCampos.add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField();
		panelCampos.add(txtIdFactura);

		panelCampos.add(new JLabel("ID Libro:"));
		txtIdLibro = new JTextField();
		panelCampos.add(txtIdLibro);

		panelCampos.add(new JLabel("Cantidad:"));
		txtCantidad = new JTextField();
		panelCampos.add(txtCantidad);

		panelCampos.add(new JLabel("Precio unitario:"));
		txtPrecio = new JTextField();
		panelCampos.add(txtPrecio);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> anadirLibro());

		add(panelCampos, BorderLayout.CENTER);
		add(btnAceptar, BorderLayout.SOUTH);
	}

	private void anadirLibro() {
		try {
			int idFactura = Integer.parseInt(txtIdFactura.getText().trim());
			int idLibro = Integer.parseInt(txtIdLibro.getText().trim());
			int cantidad = Integer.parseInt(txtCantidad.getText().trim());
			double precio = Double.parseDouble(txtPrecio.getText().trim());

			boolean ok = SistemaBDL.getInstance().anadirLibroAVenta(idFactura, idLibro, cantidad, precio);

			if (ok) {
				JOptionPane.showMessageDialog(this, "Libro añadido correctamente.");
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo añadir el libro.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
