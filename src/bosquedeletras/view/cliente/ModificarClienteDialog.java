package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Cliente;
import bosquedeletras.utils.Utils;

public class ModificarClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioClientePanel formulario;
	private Cliente cliente;

	public ModificarClienteDialog(Dialog parent, Cliente cliente) {
		super(parent, "MODIFICAR CLIENTE", true);
		this.cliente = cliente;

		setSize(500, 320);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarDatos();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioClientePanel();
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
		formulario.setNombre(cliente.getNombre());
		formulario.setPrimerApellido(cliente.getPrimerApellido());
		formulario.setSegundoApellido(cliente.getSegundoApellido());
		formulario.setDni(cliente.getDni());
		formulario.setEmail(cliente.getEmail());

		formulario.setDniEditable(false);
	}

	private void guardarCambios() {
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

		boolean modificacionCorrecta = SistemaBDL.getInstance().modificarCliente(nombre, primerApellido,
				segundoApellido, dni, email);

		if (modificacionCorrecta) {
			JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "MODIFICACIÓN CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el cliente.", "ERROR MODIFICACIÓN",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
