package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;

public class AltaLibroDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioLibroPanel formulario;

	public AltaLibroDialog(Frame parent) {
		super(parent, "ALTA LIBRO", true);
		setSize(500, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		formulario = new FormularioLibroPanel();
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
		String titulo = formulario.getTitulo();
		String autor = formulario.getAutor();
		String isbn = formulario.getIsbn();
		String editorial = formulario.getEditorial();
		String anoTexto = formulario.getAno();

		if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || anoTexto.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "DATOS INCOMPLETOS",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int ano;
		try {
			ano = Integer.parseInt(anoTexto);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El año debe ser un número entero.", "DATOS INCORRECTOS",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean altaCorrecta = SistemaBDL.getInstance().getControlLibro().altaLibro(titulo, autor, isbn, editorial,
				ano);

		if (altaCorrecta) {
			JOptionPane.showMessageDialog(this, "Libro dado de alta correctamente.", "ALTA CORRECTA",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"No se ha podido dar de alta al libro. Ya existe un libro activo con ese ISBN.", "ERROR ALTA",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}