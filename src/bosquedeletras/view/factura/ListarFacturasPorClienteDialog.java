package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;

public class ListarFacturasPorClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtIdCliente;
	private JTable tabla;
	private DefaultTableModel modeloTabla;

	public ListarFacturasPorClienteDialog(Frame parent) {
		super(parent, "LISTAR FACTURAS POR CLIENTE", true);
		setSize(700, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		panelSuperior.add(new JLabel("ID Cliente:"));
		txtIdCliente = new JTextField(10);
		panelSuperior.add(txtIdCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> cargarFacturas());
		panelSuperior.add(btnBuscar);

		add(panelSuperior, BorderLayout.NORTH);

		String[] columnas = { "ID", "Fecha", "Cliente", "Vendedor", "Total", "Cerrada" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabla = new JTable(modeloTabla);
		add(new JScrollPane(tabla), BorderLayout.CENTER);

		JPanel panelInferior = new JPanel();
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		panelInferior.add(btnCerrar);

		add(panelInferior, BorderLayout.SOUTH);
	}

	private void cargarFacturas() {
		try {
			int idCliente = Integer.parseInt(txtIdCliente.getText().trim());

			modeloTabla.setRowCount(0);
			List<Factura> facturas = SistemaBDL.getInstance().getControlFactura().listarFacturasPorCliente(idCliente);

			for (Factura f : facturas) {
				Object[] fila = { f.getId(), f.getFecha(), f.getIdCliente(), f.getIdVendedor(), f.getTotal(),
						f.isCerrada() ? "Sí" : "No" };
				modeloTabla.addRow(fila);
			}

			if (facturas.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No hay facturas para ese cliente.", "SIN RESULTADOS",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}