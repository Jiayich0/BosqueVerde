package bosquedeletras.view.factura;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioBuscarFacturaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtIdFactura;

	public FormularioBuscarFacturaPanel() {
		setLayout(new GridLayout(1, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField();
		add(txtIdFactura);
	}

	public int getIdFactura() {
		return Integer.parseInt(txtIdFactura.getText().trim());
	}

	public void setIdFactura(int idFactura) {
		txtIdFactura.setText(String.valueOf(idFactura));
	}
}
