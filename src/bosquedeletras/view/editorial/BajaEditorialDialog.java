package bosquedeletras.view.editorial;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Editorial;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BajaEditorialDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtId;

	public BajaEditorialDialog(Frame parent) {
		super(parent, "BAJA EDITORIAL", true);
		setSize(400, 180);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("ID Editorial:"));
		txtId = new JTextField(15);
		panelCentro.add(txtId);

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
		String id = txtId.getText().trim().toUpperCase();

		// Comprobar validez sintáctica
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.",
					"DATOS NO VÁLIDOS - BAJA EDITORIAL", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Buscar editorial por ID
		Editorial editorial = SistemaBDL.getInstance().getControlEditorial().buscarEditorial(id);

		// [No encontrado]
		if (editorial == null) {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja. No existe una editorial con ese ID.",
					"EDITORIAL NO ENCONTRADA", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar estado editorial - [Inactivo]
		if (!editorial.isActivo()) {
			JOptionPane.showMessageDialog(this, "La editorial ya está dada de baja.", "EDITORIAL YA INACTIVA",
					JOptionPane.WARNING_MESSAGE);
			dispose();
			return;
		}

		// Confirmar que no tiene libro asociado
		boolean tieneLibros = SistemaBDL.getInstance().getControlEditorial().tieneLibrosAsociados(id);

		if (tieneLibros) {
			JOptionPane.showMessageDialog(this, "No se puede dar de baja la editorial porque tiene libros asociados.",
					"ERROR - LIBROS ASOCIADOS", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Confirmar baja
		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que quieres dar de baja la editorial con ID " + id + "?", "CONFIRMAR BAJA",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		// Usuario cancelado
		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		// Realizar borrado (activo = false)
		boolean bajaCorrecta = SistemaBDL.getInstance().getControlEditorial().bajaEditorial(id);

		// Confirmar baja
		if (bajaCorrecta) {
			JOptionPane.showMessageDialog(this, "Editorial dada de baja correctamente.", "BAJA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja la editorial.", "ERROR BAJA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}