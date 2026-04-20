package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bosquedeletras.view.MainWindow;

public class LibroWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public LibroWindow() {
		setTitle("LIBRO");
		setSize(360, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(15, 15));

		JPanel panelBotones = crearPanelBotones();
		JPanel panelInferior = crearPanelInferior();

		add(panelBotones, BorderLayout.CENTER);
		add(panelInferior, BorderLayout.SOUTH);
	}

	private JPanel crearPanelBotones() {
		JPanel contenedor = new JPanel(new BorderLayout());
		contenedor.setBorder(BorderFactory.createEmptyBorder(45, 72, 0, 72));

		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 15));

		JButton btnAlta = crearBotonSubsistema("ALTA LIBRO");
		JButton btnBaja = crearBotonSubsistema("BAJA LIBRO");
		JButton btnModificar = crearBotonSubsistema("MODIFICAR LIBRO");
		JButton btnLeer = crearBotonSubsistema("LEER LIBRO");
		JButton btnListar = crearBotonSubsistema("LISTAR LIBROS");

		btnAlta.addActionListener(e -> abrirAltaLibro());
		// btnBaja.addActionListener(e -> ());
		btnModificar.addActionListener(e -> abrirModificarLibro());
		btnLeer.addActionListener(e -> abrirLeerLibro());
		// btnListar.addActionListener(e -> ());

		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnLeer);
		panel.add(btnListar);

		contenedor.add(panel, BorderLayout.CENTER);
		return contenedor;
	}

	private JPanel crearPanelInferior() {
		JPanel panel = new JPanel();

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(e -> volverMenuPrincipal());
		panel.add(btnVolver);

		return panel;
	}

	private JButton crearBotonSubsistema(String texto) {
		JButton boton = new JButton(texto);
		boton.setPreferredSize(new Dimension(120, 40));
		boton.setFont(new Font("SansSerif", Font.BOLD, 14));
		return boton;
	}

	private void abrirAltaLibro() {
		AltaLibroDialog dialog = new AltaLibroDialog(this);
		dialog.setVisible(true);
	}

	private void abrirModificarLibro() {
		BuscarLibroDialog dialog = new BuscarLibroDialog(this, "modificar");
		dialog.setVisible(true);
	}

	private void abrirLeerLibro() {
		BuscarLibroDialog dialog = new BuscarLibroDialog(this, "leer");
		dialog.setVisible(true);
	}

	private void volverMenuPrincipal() {
		MainWindow ventanaPrincipal = new MainWindow();
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}