package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;

public class ListarFacturasDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;

	public ListarFacturasDialog(Frame parent) {
		super(parent, "LISTAR FACTURAS", true);
		setSize(700, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarFacturas();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "Fecha", "Cliente", "Vendedor", "Total", "Cerrada" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);

		add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		panelBotones.add(btnCerrar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void cargarFacturas() {
		modeloTabla.setRowCount(0);

		List<Factura> facturas = SistemaBDL.getInstance().getControlFactura().listarFacturas();

		for (Factura f : facturas) {
			Object[] fila = { f.getId(), f.getFecha(), f.getIdCliente(), f.getIdVendedor(), f.getTotal(),
					f.isCerrada() ? "Sí" : "No" };
			modeloTabla.addRow(fila);
		}
	}
}