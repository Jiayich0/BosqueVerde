package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;
import bosquedeletras.model.LineaFactura;

public class LeerFacturaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtIdFactura;
	private JTextArea areaLineas;
	private JPanel panelDatos;

	public LeerFacturaDialog(Frame parent) {
		super(parent, "LEER FACTURA", true);
		setSize(700, 450);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(15, 15));

		JPanel panelSuperior = new JPanel();
		panelSuperior.add(new JLabel("ID Factura:"));
		txtIdFactura = new JTextField(15);
		panelSuperior.add(txtIdFactura);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> buscarFactura());
		panelSuperior.add(btnBuscar);

		add(panelSuperior, BorderLayout.NORTH);

		panelDatos = new JPanel(new GridLayout(5, 2, 10, 12));
		add(panelDatos, BorderLayout.CENTER);

		areaLineas = new JTextArea();
		areaLineas.setEditable(false);
		add(new JScrollPane(areaLineas), BorderLayout.SOUTH);

		JPanel panelInferior = new JPanel();
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		panelInferior.add(btnCerrar);

		add(panelInferior, BorderLayout.PAGE_END);
	}

	private void buscarFactura() {
		try {
			int idFactura = Integer.parseInt(txtIdFactura.getText().trim());

			Factura factura = SistemaBDL.getInstance().leerFactura(idFactura);
			if (factura == null) {
				JOptionPane.showMessageDialog(this, "La factura indicada no existe.", "NO EXISTE",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			mostrarFactura(factura);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos.", "INCORRECTO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarFactura(Factura factura) {
		panelDatos.removeAll();

		panelDatos.add(new JLabel("ID:"));
		panelDatos.add(new JLabel(String.valueOf(factura.getId())));

		panelDatos.add(new JLabel("Fecha:"));
		panelDatos.add(new JLabel(factura.getFecha()));

		panelDatos.add(new JLabel("Cliente:"));
		panelDatos.add(new JLabel(String.valueOf(factura.getIdCliente())));

		panelDatos.add(new JLabel("Vendedor:"));
		panelDatos.add(new JLabel(String.valueOf(factura.getIdVendedor())));

		panelDatos.add(new JLabel("Total:"));
		panelDatos.add(new JLabel(String.valueOf(factura.getTotal())));

		List<LineaFactura> lineas = SistemaBDL.getInstance().leerLineasFactura(factura.getId());
		StringBuilder sb = new StringBuilder();
		for (LineaFactura linea : lineas) {
			sb.append(linea).append("\n");
		}
		areaLineas.setText(sb.toString());

		panelDatos.revalidate();
		panelDatos.repaint();
	}
}
