package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;
import bosquedeletras.model.LineaFactura;

public class LeerFacturaDialog extends JDialog {

	private JTextField txtIdFactura;
	private JTextArea areaResultado;

	public LeerFacturaDialog(java.awt.Frame owner) {
		super(owner, "Leer factura", true);

		setSize(500, 400);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 5, 5));
		panelSuperior.add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField();
		panelSuperior.add(txtIdFactura);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> leerFactura());
		panelSuperior.add(btnBuscar);

		areaResultado = new JTextArea();
		areaResultado.setEditable(false);

		add(panelSuperior, BorderLayout.NORTH);
		add(new JScrollPane(areaResultado), BorderLayout.CENTER);
	}

	private void leerFactura() {
		try {
			int idFactura = Integer.parseInt(txtIdFactura.getText().trim());

			Factura factura = SistemaBDL.getInstance().leerFactura(idFactura);
			List<LineaFactura> lineas = SistemaBDL.getInstance().leerLineasFactura(idFactura);

			if (factura == null) {
				JOptionPane.showMessageDialog(this, "Factura no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			StringBuilder sb = new StringBuilder();
			sb.append(factura).append("\n\n");
			sb.append("Líneas:\n");

			for (LineaFactura linea : lineas) {
				sb.append(linea).append("\n");
			}

			areaResultado.setText(sb.toString());

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
