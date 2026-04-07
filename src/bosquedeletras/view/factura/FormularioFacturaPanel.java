package bosquedeletras.view.factura;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioFacturaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtFecha;
	private JTextField txtIdCliente;
	private JTextField txtIdVendedor;

	public FormularioFacturaPanel() {
		setLayout(new GridLayout(3, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		add(new JLabel("Fecha:"));
		txtFecha = new JTextField();
		add(txtFecha);

		add(new JLabel("ID Cliente:"));
		txtIdCliente = new JTextField();
		add(txtIdCliente);

		add(new JLabel("ID Vendedor:"));
		txtIdVendedor = new JTextField();
		add(txtIdVendedor);
	}

	public String getFecha() {
		return txtFecha.getText().trim();
	}

	public int getIdCliente() {
		return Integer.parseInt(txtIdCliente.getText().trim());
	}

	public int getIdVendedor() {
		return Integer.parseInt(txtIdVendedor.getText().trim());
	}
}
