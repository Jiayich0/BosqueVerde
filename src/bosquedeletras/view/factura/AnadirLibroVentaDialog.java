package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;

public class AnadirLibroVentaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioLineaFacturaPanel formulario;

	public AnadirLibroVentaDialog(Frame parent) {
		super(parent, "AÑADIR LIBRO A VENTA", true);
		setSize(500, 280);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioLineaFacturaPanel();
		add(formulario, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnOk = new JButton("OK");
		JButton btnCancelar = new JButton("Cancelar");

		btnOk.addActionListener(e -> aceptar());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnOk);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void aceptar() {
		try {
			int idFactura = formulario.getIdFactura();
			int idLibro = formulario.getIdLibro();
			int cantidad = formulario.getCantidad();
			double precioUnitario = formulario.getPrecioUnitario();

			boolean ok = SistemaBDL.getInstance().anadirLibroAVenta(idFactura, idLibro, cantidad, precioUnitario);

			if (ok) {
				JOptionPane.showMessageDialog(this, "Libro añadido a la venta correctamente.",
						"OPERACIÓN CORRECTA", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se ha podido añadir el libro a la venta.",
						"ERROR OPERACIÓN", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
