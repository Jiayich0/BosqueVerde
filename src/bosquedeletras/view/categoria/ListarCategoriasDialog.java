package bosquedeletras.view.categoria;

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
import bosquedeletras.model.Categoria;

public class ListarCategoriasDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;

	public ListarCategoriasDialog(Frame parent) {
		super(parent, "LISTAR CATEGORIAS", true);
		setSize(600, 350);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarCategorias();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "Nombre", "Descripción" };
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

	private void cargarCategorias() {
		modeloTabla.setRowCount(0);

		List<Categoria> categorias = SistemaBDL.getInstance().getControlCategoria().listarCategorias();

		for (Categoria c : categorias) {
			Object[] fila = {
				c.getId(),
				c.getNombre(),
				c.getDescripcion() != null ? c.getDescripcion() : "-"
			};
			modeloTabla.addRow(fila);
		}
	}
}