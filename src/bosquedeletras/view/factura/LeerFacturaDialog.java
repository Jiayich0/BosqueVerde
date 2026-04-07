package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;
import bosquedeletras.model.LineaFactura;
import bosquedeletras.utils.Utils;

public class LeerFacturaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LeerFacturaDialog(Frame parent, Factura factura) {
		super(parent, "LEER FACTURA", true);
		setSize(800, 450);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents(factura);
	}

	private void initComponents(Factura factura) {
		setLayout(new BorderLayout(15, 15));

		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 20));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel panelImagen = crearPanelImagen();
		JPanel panelDerecho = crearPanelDerecho(factura);

		panelCentral.add(panelImagen);
		panelCentral.add(panelDerecho);

		add(panelCentral, BorderLayout.CENTER);

		JPanel panelBoton = new JPanel();
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(e -> dispose());
		panelBoton.add(btnOk);

		add(panelBoton, BorderLayout.SOUTH);
	}

	private JPanel crearPanelImagen() {
		JPanel panel = new JPanel(new BorderLayout());

		JLabel lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(JLabel.CENTER);

		ImageIcon icon = new ImageIcon(Utils.FACTURA_IMG);
		Image imagenEscalada = icon.getImage().getScaledInstance(-1, 260, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(imagenEscalada));

		panel.add(lblImagen, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelDerecho(Factura factura) {
		JPanel panel = new JPanel(new BorderLayout(10, 10));

		JPanel panelDatos = new JPanel(new GridLayout(5, 2, 10, 12));

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

		JTextArea areaLineas = new JTextArea();
		areaLineas.setEditable(false);

		List<LineaFactura> lineas = SistemaBDL.getInstance().getControlFactura().leerLineasFactura(factura.getId());
		StringBuilder sb = new StringBuilder();
		for (LineaFactura linea : lineas) {
			sb.append(linea).append("\n");
		}
		areaLineas.setText(sb.toString());

		panel.add(panelDatos, BorderLayout.NORTH);
		panel.add(new JScrollPane(areaLineas), BorderLayout.CENTER);

		return panel;
	}
}
