package bosquedeletras.view.editorial;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Editorial;

public class BuscarEditorialDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtId;
	private String accion;

	public BuscarEditorialDialog(Frame parent, String accion) {
		super(parent, "BUSCAR EDITORIAL", true);
		this.accion = accion;

		setSize(350, 150);
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

		JButton btnBuscar = new JButton("Buscar");
		JButton btnCancelar = new JButton("Cancelar");

		btnBuscar.addActionListener(e -> buscar());
		btnCancelar.addActionListener(e -> dispose());

		panelBotones.add(btnBuscar);
		panelBotones.add(btnCancelar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void buscar() {
		String id = txtId.getText().trim().toUpperCase();

		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El ID es obligatorio.", "DATOS INCOMPLETOS",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Editorial editorial = SistemaBDL.getInstance().getControlEditorial().buscarEditorial(id);

		if (editorial == null) {
			JOptionPane.showMessageDialog(this, "La editorial indicada no existe.", "NO EXISTE",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ("leer".equals(accion)) {
			LeerEditorialDialog dialog = new LeerEditorialDialog(this, editorial);
			dialog.setVisible(true);
		} else if ("modificar".equals(accion)) {
			ModificarEditorialDialog dialog = new ModificarEditorialDialog(this, editorial);
			dialog.setVisible(true);
		}

		dispose();
	}
}