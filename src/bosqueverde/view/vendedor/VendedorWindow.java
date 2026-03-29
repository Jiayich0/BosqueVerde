package bosqueverde.view.vendedor;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bosqueverde.view.MainWindow;

public class VendedorWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public VendedorWindow() {
		setTitle("VENDEDOR");
		setSize(900, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 60));

		JButton btnAlta = new JButton("ALTA VENDEDOR");
		JButton btnBaja = new JButton("BAJA VENDEDOR");
		JButton btnModificar = new JButton("MODIFICAR VENDEDOR");
		JButton btnLeer = new JButton("LEER VENDEDOR");
		JButton btnListar = new JButton("LISTAR VENDEDORES");
		JButton btnVolver = new JButton("Volver");

		btnAlta.addActionListener(e -> abrirAlta());
		// otros
		btnVolver.addActionListener(e -> volverMenuPrincipal());

		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnLeer);
		panel.add(btnListar);
		panel.add(btnVolver);

		add(panel);
	}

	private void abrirAlta() {
		AltaVendedorDialog dialog = new AltaVendedorDialog(this);
		dialog.setVisible(true);
	}

	// add

	private void volverMenuPrincipal() {
		MainWindow ventanaPrincipal = new MainWindow();
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}