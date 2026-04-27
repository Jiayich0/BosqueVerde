package bosquedeletras.view.libro;

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

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Libro;
import bosquedeletras.strategy.SortIdStrategy;
import bosquedeletras.strategy.SortStrategy;

public class ListarLibroDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JComboBox<SortStrategy<Libro>> comboOrden;

	public ListarLibroDialog(Frame parent) {
		super(parent, "LISTAR LIBROS", true);
		setSize(750, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarLibros();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		String[] columnas = { "ID", "Título", "Autor", "ISBN", "Editorial", "Año", "ID Categoría" };

		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		comboOrden = new JComboBox<>();
		comboOrden.addItem(new SortIdStrategy<Libro>(true));
		comboOrden.addItem(new SortIdStrategy<Libro>(false));
		comboOrden.addActionListener(e -> cargarLibros());

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

	private void cargarLibros() {
		modeloTabla.setRowCount(0);

		@SuppressWarnings("unchecked")
		SortStrategy<Libro> strategy = (SortStrategy<Libro>) comboOrden.getSelectedItem();

		List<Libro> libros = SistemaBDL.getInstance().getControlLibro().listarLibros(strategy);

		for (Libro l : libros) {
			Object[] fila = {
					l.getId(),
					l.getTitulo(),
					l.getAutor(),
					l.getIsbn(),
					l.getEditorial(),
					l.getAno(),
					l.getIdCategoria()
			};

			modeloTabla.addRow(fila);
		}
	}
}