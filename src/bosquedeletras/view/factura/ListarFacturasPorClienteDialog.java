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

public class ListarFacturasPorClienteDialog extends JDialog {

	private JTextField txtIdCliente;
	private JTextArea areaResultado;

	public ListarFacturasPorClienteDialog(java.awt.Frame owner) {
		super(owner, "Listar facturas por cliente", true);

		setSize(500, 400);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 5, 5));
		panelSuperior.add(new JLabel("ID Cliente:"));
		txtIdCliente = new JTextField();
		panelSuperior.add(txtIdCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> listarPorCliente());
		panelSuperior.add(btnBuscar);

		areaResultado = new JTextArea();
		areaResultado.setEditable(false);

		add(panelSuperior, BorderLayout.NORTH);
		add(new JScrollPane(areaResultado), BorderLayout.CENTER);
	}

	private void listarPorCliente() {
		try {
			int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
			List<Factura> facturas = SistemaBDL.getInstance().listarFacturasPorCliente(idCliente);

			if (facturas.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No hay facturas para ese cliente.", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}

			StringBuilder sb = new StringBuilder();
			for (Factura factura : facturas) {
				sb.append(factura).append("\n");
			}

			areaResultado.setText(sb.toString());

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
