package bosquedeletras.view.vendedor;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Vendedor;
import bosquedeletras.utils.Utils;

public class ModificarVendedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioVendedorPanel formulario;
	private Vendedor vendedor;

	public ModificarVendedorDialog(Dialog parent, Vendedor vendedor) {
		super(parent, "MODIFICAR VENDEDOR", true);
		this.vendedor = vendedor;

		setSize(500, 320);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		cargarDatos();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioVendedorPanel();
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
		formulario.setNombre(vendedor.getNombre());
		formulario.setPrimerApellido(vendedor.getPrimerApellido());
		formulario.setSegundoApellido(vendedor.getSegundoApellido());
		formulario.setDni(vendedor.getDni());
		formulario.setTelefono(vendedor.getTelefono());

		formulario.setDniEditable(false);
	}

	private void guardarCambios() {
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

		boolean modificacionCorrecta = SistemaBDL.getInstance().getControlVendedor().modificarVendedor(nombre, primerApellido,
				segundoApellido, dni, telefono);

		if (modificacionCorrecta) {
			JOptionPane.showMessageDialog(this, "Vendedor modificado correctamente.", "MODIFICACIÓN CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el vendedor.", "ERROR MODIFICACIÓN",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}