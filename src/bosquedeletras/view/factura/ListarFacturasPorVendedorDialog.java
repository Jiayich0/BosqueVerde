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

public class ListarFacturasPorVendedorDialog extends JDialog {

	private JTextField txtIdVendedor;
	private JTextArea areaResultado;

	public ListarFacturasPorVendedorDialog(java.awt.Frame owner) {
		super(owner, "Listar facturas por vendedor", true);

		setSize(500, 400);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 5, 5));
		panelSuperior.add(new JLabel("ID Vendedor:"));
		txtIdVendedor = new JTextField();
		panelSuperior.add(txtIdVendedor);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> listarPorVendedor());
		panelSuperior.add(btnBuscar);

		areaResultado = new JTextArea();
		areaResultado.setEditable(false);

		add(panelSuperior, BorderLayout.NORTH);
		add(new JScrollPane(areaResultado), BorderLayout.CENTER);
	}

	private void listarPorVendedor() {
		try {
			int idVendedor = Integer.parseInt(txtIdVendedor.getText().trim());
			List<Factura> facturas = SistemaBDL.getInstance().listarFacturasPorVendedor(idVendedor);

			if (facturas.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No hay facturas para ese vendedor.", "Información",
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
