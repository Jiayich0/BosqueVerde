package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;

public class AbrirVentaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioFacturaPanel formulario;

	public AbrirVentaDialog(Frame parent) {
		super(parent, "ABRIR VENTA", true);
		setSize(500, 240);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioFacturaPanel();
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
			String fecha = formulario.getFecha();
			int idCliente = formulario.getIdCliente();
			int idVendedor = formulario.getIdVendedor();

			if (fecha.isEmpty()) {
				JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "DATOS INCOMPLETOS",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			int idFactura = SistemaBDL.getInstance().getControlFactura().abrirVenta(fecha, idCliente, idVendedor);

			if (idFactura > 0) {
				JOptionPane.showMessageDialog(this, "Venta abierta correctamente. ID factura: " + idFactura,
						"APERTURA CORRECTA", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this,
						"No se ha podido abrir la venta. Comprueba cliente, vendedor y fecha.",
						"ERROR APERTURA", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
