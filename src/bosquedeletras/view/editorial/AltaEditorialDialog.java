package bosquedeletras.view.editorial;

import bosquedeletras.facade.SistemaBDL;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AltaEditorialDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioEditorialPanel formulario;

	public AltaEditorialDialog(Frame parent) {
		super(parent, "ALTA EDITORIAL", true);
		setSize(500, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioEditorialPanel();
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
		String id = formulario.getId();
		String nombre = formulario.getNombre();

		// Validación de datos obligatorios
		if (id.isEmpty() || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ID Editorial y Nombre son obligatorios.", "DATOS INCOMPLETOS",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int resultado = SistemaBDL.getInstance().getControlEditorial().altaEditorial(id, nombre);

		if (resultado == 1) {
			JOptionPane.showMessageDialog(this, "Editorial dada de alta correctamente.", "ALTA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else if (resultado == 2) {
			JOptionPane.showMessageDialog(this, "Editorial reactivada correctamente.", "REACTIVACIÓN CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de alta. Ya existe una editorial activa con ese ID.",
					"ERROR - EDITORIAL EXISTENTE", JOptionPane.ERROR_MESSAGE);
		}
	}
}