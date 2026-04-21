package bosquedeletras.view.cliente;

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
import bosquedeletras.model.Cliente;

public class ListarClientesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;

	public ListarClientesDialog(Frame parent) {
		super(parent, "LISTAR CLIENTES", true);
		setSize(750, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarClientes();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "Nombre", "Primer Apellido", "Segundo Apellido", "DNI", "Email" };
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

	private void cargarClientes() {
		modeloTabla.setRowCount(0);

		List<Cliente> clientes = SistemaBDL.getInstance().listarClientes();

		for (Cliente c : clientes) {
			Object[] fila = {
				c.getId(),
				c.getNombre(),
				c.getPrimerApellido(),
				c.getSegundoApellido() != null ? c.getSegundoApellido() : "-",
				c.getDni(),
				c.getEmail() != null ? c.getEmail() : "-"
			};
			modeloTabla.addRow(fila);
		}
	}
}
