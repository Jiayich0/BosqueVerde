package bosquedeletras.view.editorial;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Editorial;
import bosquedeletras.strategy.SortIdStrategy;
import bosquedeletras.strategy.SortStrategy;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarEditorialesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JComboBox<SortStrategy<Editorial>> comboOrden;

	public ListarEditorialesDialog(Frame parent) {
		super(parent, "LISTAR EDITORIALES", true);
		setSize(600, 350);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarEditoriales();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "ID Editorial", "Nombre", "Activo" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		comboOrden = new JComboBox<>();
		comboOrden.addItem(new SortIdStrategy<Editorial>(true));
		comboOrden.addItem(new SortIdStrategy<Editorial>(false));
		comboOrden.addActionListener(e -> cargarEditoriales());
		JPanel panelControl = new JPanel();
		panelControl.add(new JLabel("Ordenar por:"));
		panelControl.add(comboOrden);
		
		add(panelControl, BorderLayout.NORTH);
		
		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);

		add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		panelBotones.add(btnCerrar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void cargarEditoriales() {
		modeloTabla.setRowCount(0);
		
		@SuppressWarnings("unchecked")
		SortStrategy<Editorial> strategy = (SortStrategy<Editorial>) comboOrden.getSelectedItem();

		List<Editorial> editoriales = SistemaBDL.getInstance().getControlEditorial().listarEditoriales(strategy);

		for (Editorial e : editoriales) {
			Object[] fila = { e.getId(), e.getIdEditorial(), e.getNombre(), e.isActivo() ? "Sí" : "No" };
			modeloTabla.addRow(fila);
		}
	}
}