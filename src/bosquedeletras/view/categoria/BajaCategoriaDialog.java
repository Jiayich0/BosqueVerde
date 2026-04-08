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

public class BajaCategoriaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;

	public BajaCategoriaDialog(Frame parent) {
		super(parent, "BAJA CATEGORIA", true);
		setSize(400, 180);
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

		JButton btnDarBaja = new JButton("Dar de baja");
		JButton btnCancelar = new JButton("Cancelar");

		btnDarBaja.addActionListener(e -> darBaja());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnDarBaja);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void darBaja() {
		String nombre = txtNombre.getText().trim();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO - BAJA CATEGORIA",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Categoria categoria = SistemaBDL.getInstance().getControlCategoria().leerCategoria(nombre);

		if (categoria == null) {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja. No existe una categoría con ese nombre.",
					"NO EXISTENTE - BAJA CATEGORIA", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que quieres dar de baja la categoría " + nombre + "?", "CONFIRMAR BAJA",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		boolean bajaCorrecta = SistemaBDL.getInstance().getControlCategoria().bajaCategoria(nombre);

		if (bajaCorrecta) {
			JOptionPane.showMessageDialog(this, "Categoría dada de baja correctamente.", "BAJA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja la categoría.", "ERROR BAJA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}