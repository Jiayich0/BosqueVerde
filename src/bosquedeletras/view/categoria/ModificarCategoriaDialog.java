package bosquedeletras.view.categoria;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Categoria;

public class ModificarCategoriaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioCategoriaPanel formulario;
	private Categoria categoria;

	public ModificarCategoriaDialog(Dialog parent, Categoria categoria) {
		super(parent, "MODIFICAR CATEGORIA", true);
		this.categoria = categoria;

		setSize(400, 220);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarDatos();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioCategoriaPanel();
		add(formulario, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnGuardar = new JButton("Guardar");
		JButton btnCancelar = new JButton("Cancelar");

		btnGuardar.addActionListener(e -> guardarCambios());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void cargarDatos() {
		formulario.setNombre(categoria.getNombre());
		formulario.setDescripcion(categoria.getDescripcion());
	}

	private void guardarCambios() {
		String nombre = formulario.getNombre();
		String descripcion = formulario.getDescripcion();

		if (nombre.isEmpty() || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nombre y descripción son obligatorios.",
					"DATOS INCOMPLETOS", JOptionPane.WARNING_MESSAGE);
			return;
		}

		boolean modificacionCorrecta = SistemaBDL.getInstance().getControlCategoria().modificarCategoria(nombre, descripcion);

		if (modificacionCorrecta) {
			JOptionPane.showMessageDialog(this, "Categoría modificada correctamente.", "MODIFICACIÓN CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la categoría.", "ERROR MODIFICACIÓN",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}