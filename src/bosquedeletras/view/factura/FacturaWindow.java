package bosquedeletras.view.factura;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FacturaWindow extends JFrame {

	public FacturaWindow() {
		setTitle("Gestión de Facturas");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

		JButton btnAbrirVenta = new JButton("Abrir venta");
		JButton btnAnadirLibro = new JButton("Añadir libro a venta");
		JButton btnCerrarVenta = new JButton("Cerrar venta");
		JButton btnLeerFactura = new JButton("Leer factura");
		JButton btnListarFacturas = new JButton("Listar facturas");
		JButton btnListarPorCliente = new JButton("Listar facturas por cliente");
		JButton btnListarPorVendedor = new JButton("Listar facturas por vendedor");

		btnAbrirVenta.addActionListener(e -> new AbrirVentaDialog(this).setVisible(true));
		btnAnadirLibro.addActionListener(e -> new AnadirLibroVentaDialog(this).setVisible(true));
		btnCerrarVenta.addActionListener(e -> new CerrarVentaDialog(this).setVisible(true));
		btnLeerFactura.addActionListener(e -> new LeerFacturaDialog(this).setVisible(true));
		btnListarFacturas.addActionListener(e -> new ListarFacturasDialog(this).setVisible(true));
		btnListarPorCliente.addActionListener(e -> new ListarFacturasPorClienteDialog(this).setVisible(true));
		btnListarPorVendedor.addActionListener(e -> new ListarFacturasPorVendedorDialog(this).setVisible(true));

		panel.add(btnAbrirVenta);
		panel.add(btnAnadirLibro);
		panel.add(btnCerrarVenta);
		panel.add(btnLeerFactura);
		panel.add(btnListarFacturas);
		panel.add(btnListarPorCliente);
		panel.add(btnListarPorVendedor);

		add(panel);
	}
}
