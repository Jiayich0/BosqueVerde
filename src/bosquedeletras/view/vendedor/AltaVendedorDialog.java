package bosquedeletras.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.utils.Utils;

public class AltaVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioVendedorPanel formulario;

	public AltaVendedorDialog(Frame parent) {
		super(parent, "ALTA VENDEDOR", true);
		setSize(500, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioVendedorPanel();
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
		String primerApellido = formulario.getPrimerApellido();
		String segundoApellido = formulario.getSegundoApellido();
		String dni = formulario.getDni();
		String telefono = formulario.getTelefono();

		if (segundoApellido.isEmpty()) {
			segundoApellido = null;
		}

		if (nombre.isEmpty() || primerApellido.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nombre, primer apellido, DNI y teléfono son obligatorios.",
					"DATOS INCOMPLETOS", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "DATOS INCORRECTOS",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!Utils.isValidTelefono(telefono)) {
			JOptionPane.showMessageDialog(this, "El teléfono introducido no es válido.", "DATOS INCORRECTOS",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean altaCorrecta = SistemaBDL.getInstance().altaVendedor(nombre, primerApellido, segundoApellido, dni,
				telefono);

		if (altaCorrecta) {
			JOptionPane.showMessageDialog(this, "Vendedor dado de alta correctamente.", "ALTA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de alta al vendedor. Ya existe un vendedor activo con ese DNI.", "ERROR ALTA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}