package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;
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

		JButton btnAbrir = crearBotonSubsistema("ABRIR VENTA");
		JButton btnAnadirLibro = crearBotonSubsistema("AÑADIR LIBRO A VENTA");
		JButton btnCerrar = crearBotonSubsistema("CERRAR VENTA");
		JButton btnLeer = crearBotonSubsistema("LEER FACTURA");
		JButton btnListar = crearBotonSubsistema("LISTAR FACTURAS");
		JButton btnListarCliente = crearBotonSubsistema("LISTAR POR CLIENTE");
		JButton btnListarVendedor = crearBotonSubsistema("LISTAR POR VENDEDOR");

		btnAbrir.addActionListener(e -> abrirVenta());
		btnAnadirLibro.addActionListener(e -> abrirAnadirLibro());
		btnCerrar.addActionListener(e -> abrirCerrarVenta());
		btnLeer.addActionListener(e -> abrirLeerFactura());
		btnListar.addActionListener(e -> abrirListarFacturas());
		btnListarCliente.addActionListener(e -> abrirListarPorCliente());
		btnListarVendedor.addActionListener(e -> abrirListarPorVendedor());

		panel.add(btnAbrir);
		panel.add(btnAnadirLibro);
		panel.add(btnCerrar);
		panel.add(btnLeer);
		panel.add(btnListar);
		panel.add(btnListarCliente);
		panel.add(btnListarVendedor);

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
		String input = JOptionPane.showInputDialog(this, "Introduce el ID de la factura:");
		if (input == null || input.isBlank()) {
			return;
		}

		try {
			int idFactura = Integer.parseInt(input);
			Factura factura = SistemaBDL.getInstance().getControlFactura().leerFactura(idFactura);

			if (factura == null) {
				JOptionPane.showMessageDialog(this, "No existe ninguna factura con ese ID.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			LeerFacturaDialog dialog = new LeerFacturaDialog(this, factura);
			dialog.setVisible(true);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El ID debe ser numérico.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
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
