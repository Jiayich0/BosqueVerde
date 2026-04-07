package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bosquedeletras.view.MainWindow;

public class FacturaWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public FacturaWindow() {
		setTitle("FACTURA");
		setSize(420, 500);
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
		contenedor.setBorder(BorderFactory.createEmptyBorder(35, 72, 0, 72));

		JPanel panel = new JPanel(new GridLayout(8, 1, 0, 15));

		JButton btnAbrirVenta = crearBotonSubsistema("ABRIR VENTA");
		JButton btnAnadirLibro = crearBotonSubsistema("AÑADIR LIBRO A VENTA");
		JButton btnCerrarVenta = crearBotonSubsistema("CERRAR VENTA");
		JButton btnLeerFactura = crearBotonSubsistema("LEER FACTURA");
		JButton btnListarFacturas = crearBotonSubsistema("LISTAR FACTURAS");
		JButton btnListarPorCliente = crearBotonSubsistema("LISTAR POR CLIENTE");
		JButton btnListarPorVendedor = crearBotonSubsistema("LISTAR POR VENDEDOR");

		btnAbrirVenta.addActionListener(e -> abrirVenta());
		btnAnadirLibro.addActionListener(e -> abrirAnadirLibro());
		btnCerrarVenta.addActionListener(e -> abrirCerrarVenta());
		btnLeerFactura.addActionListener(e -> abrirLeerFactura());
		btnListarFacturas.addActionListener(e -> abrirListarFacturas());
		btnListarPorCliente.addActionListener(e -> abrirListarPorCliente());
		btnListarPorVendedor.addActionListener(e -> abrirListarPorVendedor());

		panel.add(btnAbrirVenta);
		panel.add(btnAnadirLibro);
		panel.add(btnCerrarVenta);
		panel.add(btnLeerFactura);
		panel.add(btnListarFacturas);
		panel.add(btnListarPorCliente);
		panel.add(btnListarPorVendedor);

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
		boton.setPreferredSize(new Dimension(60, 40));
		boton.setFont(new Font("SansSerif", Font.BOLD, 14));
		return boton;
	}

	private void abrirVenta() {
		AbrirVentaDialog dialog = new AbrirVentaDialog(this);
		dialog.setVisible(true);
	}

	private void abrirAnadirLibro() {
		AnadirLibroVentaDialog dialog = new AnadirLibroVentaDialog(this);
		dialog.setVisible(true);
	}

	private void abrirCerrarVenta() {
		CerrarVentaDialog dialog = new CerrarVentaDialog(this);
		dialog.setVisible(true);
	}

	private void abrirLeerFactura() {
		LeerFacturaDialog dialog = new LeerFacturaDialog(this);
		dialog.setVisible(true);
	}

	private void abrirListarFacturas() {
		ListarFacturasDialog dialog = new ListarFacturasDialog(this);
		dialog.setVisible(true);
	}

	private void abrirListarPorCliente() {
		ListarFacturasPorClienteDialog dialog = new ListarFacturasPorClienteDialog(this);
		dialog.setVisible(true);
	}

	private void abrirListarPorVendedor() {
		ListarFacturasPorVendedorDialog dialog = new ListarFacturasPorVendedorDialog(this);
		dialog.setVisible(true);
	}

	private void volverMenuPrincipal() {
		MainWindow ventanaPrincipal = new MainWindow();
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
