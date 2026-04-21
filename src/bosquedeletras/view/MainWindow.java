package bosquedeletras.view;

import bosquedeletras.utils.Utils;
import bosquedeletras.view.categoria.CategoriaWindow;
import bosquedeletras.view.cliente.ClienteWindow;
import bosquedeletras.view.editorial.EditorialWindow;
import bosquedeletras.view.libro.LibroWindow;
import bosquedeletras.view.vendedor.VendedorWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setTitle("BOSQUE DE LETRAS");
		setSize(900, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(15, 15));

		JPanel panelImagen = crearPanelImagen();
		JPanel panelBotones = crearPanelBotones();

		add(panelImagen, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}

	private JPanel crearPanelImagen() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JLabel lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon icon = new ImageIcon(Utils.BDL_IMG);
		Image imagenEscalada = icon.getImage().getScaledInstance(-1, 450, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(imagenEscalada));

		panel.add(lblImagen, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 150, 60, 150));

		JButton btnCliente = crearBotonSubsistema("CLIENTE");
		JButton btnVendedor = crearBotonSubsistema("VENDEDOR");
		JButton btnLibro = crearBotonSubsistema("LIBRO");
		JButton btnCategoria = crearBotonSubsistema("CATEGORÍA");
		JButton btnEditorial = crearBotonSubsistema("EDITORIAL");
		JButton btnFactura = crearBotonSubsistema("FACTURA");

		btnVendedor.addActionListener(e -> abrirVendedor());
		btnLibro.addActionListener(e -> abrirLibro());
		btnCategoria.addActionListener(e -> abrirCategoria());
		btnEditorial.addActionListener(e -> abrirEditorial());
		btnCliente.addActionListener(e -> abrirCliente());

		panel.add(btnCliente);
		panel.add(btnVendedor);
		panel.add(btnLibro);
		panel.add(btnCategoria);
		panel.add(btnEditorial);
		panel.add(btnFactura);

		return panel;
	}

	private JButton crearBotonSubsistema(String texto) {
		JButton boton = new JButton(texto);
		boton.setPreferredSize(new Dimension(60, 40));
		boton.setFont(new Font("SansSerif", Font.BOLD, 14));
		return boton;
	}

	private void abrirVendedor() {
		VendedorWindow ventanaVendedor = new VendedorWindow();
		ventanaVendedor.setVisible(true);
		dispose();
	}

	private void abrirLibro() {
		LibroWindow ventanaLibro = new LibroWindow();
		ventanaLibro.setVisible(true);
		dispose();
	}

	private void abrirCategoria() {
		CategoriaWindow ventanaCategoria = new CategoriaWindow();
		ventanaCategoria.setVisible(true);
		dispose();
	}

	private void abrirEditorial() {
		EditorialWindow ventanaEditorial = new EditorialWindow();
		ventanaEditorial.setVisible(true);
		dispose();
	}
	
	private void abrirCliente() {
		ClienteWindow ventanaCliente = new ClienteWindow();
		ventanaCliente.setVisible(true);
		dispose();
	}
}