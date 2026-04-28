package bosquedeletras.view.libro;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;

public class AsignarCategoriaLibroDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtIsbn;
	private JTextField txtIdCategoria;

	public AsignarCategoriaLibroDialog(JFrame parent) {
		super(parent, "Asignar libro a categoría", true);
		setSize(350, 200);
		setLocationRelativeTo(parent);
		initComponents();
	}

	private void initComponents() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

		txtIsbn = new JTextField();
		txtIdCategoria = new JTextField();

		JButton btnAsignar = new JButton("Asignar");
		JButton btnCancelar = new JButton("Cancelar");

		panel.add(new JLabel("ISBN:"));
		panel.add(txtIsbn);

		panel.add(new JLabel("ID Categoría:"));
		panel.add(txtIdCategoria);

		panel.add(btnAsignar);
		panel.add(btnCancelar);

		btnAsignar.addActionListener(e -> asignarCategoria());
		btnCancelar.addActionListener(e -> dispose());

		add(panel);
	}

	private void asignarCategoria() {
		try {
			String isbn = txtIsbn.getText();
			int idCategoria = Integer.parseInt(txtIdCategoria.getText());

			boolean resultado = SistemaBDL.getInstance().getControlLibro().asignarLibroACategoria(isbn, idCategoria);

			if (resultado) {
				JOptionPane.showMessageDialog(this, "Categoría asignada correctamente.");
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo asignar la categoría.");
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El ID de categoría debe ser un número.");
		}
	}
}