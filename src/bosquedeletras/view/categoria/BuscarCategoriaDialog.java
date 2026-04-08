package bosquedeletras.view.categoria;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Categoria;

public class BuscarCategoriaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
	private String accion;

	public BuscarCategoriaDialog(Frame parent, String accion) {
		super(parent, "BUSCAR CATEGORIA", true);
		this.accion = accion;

		setSize(350, 150);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("Nombre:"));
		txtNombre = new JTextField(15);
		panelCentro.add(txtNombre);

		add(panelCentro, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnBuscar = new JButton("Buscar");
		JButton btnCancelar = new JButton("Cancelar");

		btnBuscar.addActionListener(e -> buscar());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnBuscar);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void buscar() {
		String nombre = txtNombre.getText().trim();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Categoria categoria = SistemaBDL.getInstance().getControlCategoria().leerCategoria(nombre);

		if (categoria == null) {
			JOptionPane.showMessageDialog(this, "La categoría indicada no existe.", "NO EXISTE",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ("leer".equals(accion)) {
			LeerCategoriaDialog dialog = new LeerCategoriaDialog(this, categoria);
			dialog.setVisible(true);
		} else {
			ModificarCategoriaDialog dialog = new ModificarCategoriaDialog(this, categoria);
			dialog.setVisible(true);
		}

		dispose();
	}
}