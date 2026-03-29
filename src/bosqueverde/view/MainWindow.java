package bosqueverde.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bosqueverde.view.vendedor.VendedorWindow;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setTitle("BOSQUE DE LETRAS");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 80));

		JButton btnCliente = new JButton("CLIENTE");
		JButton btnVendedor = new JButton("VENDEDOR");
		JButton btnLibro = new JButton("LIBRO");
		JButton btnCategoria = new JButton("CATEGORÍA");
		JButton btnEditorial = new JButton("EDITORIAL");
		JButton btnFactura = new JButton("FACTURA");

		btnVendedor.addActionListener(e -> abrirVendedor());

		panelPrincipal.add(btnCliente);
		panelPrincipal.add(btnVendedor);
		panelPrincipal.add(btnLibro);
		panelPrincipal.add(btnCategoria);
		panelPrincipal.add(btnEditorial);
		panelPrincipal.add(btnFactura);

		add(panelPrincipal);
	}

	private void abrirVendedor() {
		VendedorWindow ventanaVendedor = new VendedorWindow();
		ventanaVendedor.setVisible(true);
		dispose();
	}

	// diseño de botones inicializables:
	// 1. Boton normal de funcionalidades
	// 2. Botones de navegabilidad (ok, cancelar, etc)

	// rediseñar mainWindow cmo en la SRS, mirar que usar para poder hacerlo más
	// bonito
	// y añadir png al mainwindow

	// ajustar margenes de formualrios
	// decidir como poner lo de opcional
}