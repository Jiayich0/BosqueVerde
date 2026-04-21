package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.utils.Utils;

public class AltaClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioClientePanel formulario;

	public AltaClienteDialog(Frame parent) {
		super(parent, "ALTA CLIENTE", true);
		setSize(500, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioClientePanel();
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
		String email = formulario.getEmail();

		if (segundoApellido.isEmpty()) {
			segundoApellido = null;
		}

		if (email.isEmpty()) {
			email = null;
		}

		if (nombre.isEmpty() || primerApellido.isEmpty() || dni.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nombre, primer apellido y DNI son obligatorios.",
					"DATOS INCOMPLETOS", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!Utils.isValidDni(dni)) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "DATOS INCORRECTOS",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean altaCorrecta = SistemaBDL.getInstance().altaCliente(nombre, primerApellido, segundoApellido, dni,
				email);

		if (altaCorrecta) {
			JOptionPane.showMessageDialog(this, "Cliente dado de alta correctamente.", "ALTA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de alta al cliente. Ya existe un cliente activo con ese DNI.", "ERROR ALTA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
