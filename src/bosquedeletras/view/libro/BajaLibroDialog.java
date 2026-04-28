package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Libro;

public class BajaLibroDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtIsbn;

	public BajaLibroDialog(Frame parent) {
		super(parent, "BAJA LIBRO", true);
		setSize(350, 150);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelCentro = new JPanel();
		panelCentro.add(new JLabel("ISBN:"));
		txtIsbn = new JTextField(15);
		panelCentro.add(txtIsbn);

		add(panelCentro, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();

		JButton btnBaja = new JButton("Baja");
		JButton btnCancelar = new JButton("Cancelar");

		btnBaja.addActionListener(e -> baja());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnBaja);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void baja() {
		String isbn = txtIsbn.getText().trim();

		if (isbn.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Libro libro = SistemaBDL.getInstance().getControlLibro().leerLibro(isbn);

		if (libro == null) {
			JOptionPane.showMessageDialog(this, "El libro indicado no existe.", "NO EXISTE", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int opcion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que desea dar de baja el libro?\n\n" + libro.toString(), "CONFIRMAR BAJA",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opcion != JOptionPane.YES_OPTION) {
			return;
		}

		boolean bajaCorrecta = SistemaBDL.getInstance().getControlLibro().bajaLibro(isbn);

		if (!bajaCorrecta) {
			JOptionPane.showMessageDialog(this, "No se ha podido dar de baja el libro.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Libro dado de baja correctamente.", "BAJA CORRECTA",
				JOptionPane.INFORMATION_MESSAGE);

		dispose();
	}
}