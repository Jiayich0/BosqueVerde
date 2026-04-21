package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.utils.Utils;

public class RegistrarSocioDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtDniCliente;
	private JTextField txtNumeroSocio;
	private JTextField txtFechaInscripcion;

	public RegistrarSocioDialog(Frame parent) {
		super(parent, "REGISTRAR SOCIO", true);
		setSize(480, 260);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 80, 20, 80));

		panelFormulario.add(new JLabel("DNI del cliente:"));
		txtDniCliente = new JTextField();
		panelFormulario.add(txtDniCliente);

		panelFormulario.add(new JLabel("Número de socio:"));
		txtNumeroSocio = new JTextField();
		panelFormulario.add(txtNumeroSocio);

		panelFormulario.add(new JLabel("Fecha inscripción (YYYY-MM-DD):"));
		txtFechaInscripcion = new JTextField(LocalDate.now().toString());
		panelFormulario.add(txtFechaInscripcion);

		add(panelFormulario, BorderLayout.CENTER);

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
		String dniCliente = txtDniCliente.getText().trim().toUpperCase();
		String numeroSocio = txtNumeroSocio.getText().trim();
		String fechaInscripcion = txtFechaInscripcion.getText().trim();

		if (dniCliente.isEmpty() || numeroSocio.isEmpty() || fechaInscripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "DATOS INCOMPLETOS",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!Utils.isValidDni(dniCliente)) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "DATOS INCORRECTOS",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean registroOk = SistemaBDL.getInstance().getControlCliente().registrarSocio(numeroSocio, fechaInscripcion,
				dniCliente);

		if (registroOk) {
			JOptionPane.showMessageDialog(this, "Socio registrado correctamente.", "REGISTRO CORRECTO",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido registrar el socio. El cliente no existe, ya es socio activo o el número de socio está en uso.",
					"ERROR REGISTRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
