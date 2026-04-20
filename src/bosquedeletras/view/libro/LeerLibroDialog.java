package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import bosquedeletras.model.Libro;

public class LeerLibroDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private FormularioLibroPanel formulario;

	public LeerLibroDialog(Dialog parent, Libro libro) {
		super(parent, "LEER LIBRO", true);

		setSize(520, 320);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(libro);
	}

	private void initComponents(Libro libro) {
		setLayout(new BorderLayout());

		formulario = new FormularioLibroPanel();
		formulario.setTitulo(libro.getTitulo());
		formulario.setAutor(libro.getAutor());
		formulario.setIsbn(libro.getIsbn());
		formulario.setEditorial(libro.getEditorial());
		formulario.setAno(String.valueOf(libro.getAno()));
		formulario.setAllEditable(false);

		add(formulario, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnCerrar = new JButton("Cerrar");

		btnCerrar.addActionListener(e -> dispose());

		panelBotones.add(btnCerrar);

		add(panelBotones, BorderLayout.SOUTH);
	}
}