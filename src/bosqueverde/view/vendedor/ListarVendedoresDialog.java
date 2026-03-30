package bosqueverde.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bosqueverde.facade.SistemaBV;
import bosqueverde.model.Vendedor;

public class ListarVendedoresDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;

	public ListarVendedoresDialog(Frame parent) {
		super(parent, "LISTAR VENDEDORES", true);
		setSize(700, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarVendedores();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "Nombre", "Primer Apellido", "Segundo Apellido", "DNI", "Teléfono" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override // para evitar que se pueda modificar en la vista (de usuario)
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

	private void cargarVendedores() {
		modeloTabla.setRowCount(0);

		List<Vendedor> vendedores = SistemaBV.getInstance().listarVendedores();

		for (Vendedor v : vendedores) {
			Object[] fila = { v.getId(), v.getNombre(), v.getPrimerApellido(), v.getSegundoApellido(), v.getDni(),
					v.getTelefono() };
			modeloTabla.addRow(fila);
		}
	}
}