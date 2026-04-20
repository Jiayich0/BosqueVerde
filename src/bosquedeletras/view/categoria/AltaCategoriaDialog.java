package bosquedeletras.view.categoria;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;

public class AltaCategoriaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioCategoriaPanel formulario;

	public AltaCategoriaDialog(Frame parent) {
		super(parent, "ALTA CATEGORIA", true);
		setSize(400, 200);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioCategoriaPanel();
		add(formulario, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnOk = new JButton("OK");
		JButton btnCancelar = new JButton("Cancelar");

		btnOk.addActionListener(e -> aceptar());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnOk);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void aceptar() {
		String nombre = formulario.getNombre();
		String descripcion = formulario.getDescripcion();

		if (nombre.isEmpty() || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "DATOS INCOMPLETOS",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		boolean altaCorrecta = SistemaBDL.getInstance().getControlCategoria().altaCategoria(nombre, descripcion);

		if (altaCorrecta) {
			JOptionPane.showMessageDialog(this, "Categoría dada de alta correctamente.", "ALTA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de alta la categoría. Ya existe una categoría activa con ese nombre.",
					"ERROR ALTA", JOptionPane.ERROR_MESSAGE);
		}
	}
}