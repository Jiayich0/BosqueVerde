package bosquedeletras.view.factura;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioAnadirLibroPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtIdFactura;
	private JTextField txtIdLibro;
	private JTextField txtCantidad;
	private JTextField txtPrecioUnitario;

	public FormularioAnadirLibroPanel() {
		setLayout(new GridLayout(4, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField();
		add(txtIdFactura);

		add(new JLabel("ID Libro:"));
		txtIdLibro = new JTextField();
		add(txtIdLibro);

		add(new JLabel("Cantidad:"));
		txtCantidad = new JTextField();
		add(txtCantidad);

		add(new JLabel("Precio unitario:"));
		txtPrecioUnitario = new JTextField();
		add(txtPrecioUnitario);
	}

	public int getIdFactura() {
		return Integer.parseInt(txtIdFactura.getText().trim());
	}

	public int getIdLibro() {
		return Integer.parseInt(txtIdLibro.getText().trim());
	}

	public int getCantidad() {
		return Integer.parseInt(txtCantidad.getText().trim());
	}

	public double getPrecioUnitario() {
		return Double.parseDouble(txtPrecioUnitario.getText().trim());
	}

	public void setIdFactura(int idFactura) {
		txtIdFactura.setText(String.valueOf(idFactura));
	}

	public void setIdLibro(int idLibro) {
		txtIdLibro.setText(String.valueOf(idLibro));
	}

	public void setCantidad(int cantidad) {
		txtCantidad.setText(String.valueOf(cantidad));
	}

	public void setPrecioUnitario(double precioUnitario) {
		txtPrecioUnitario.setText(String.valueOf(precioUnitario));
	}
}
