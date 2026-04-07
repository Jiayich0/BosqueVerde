package bosquedeletras.view.factura;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Factura;

public class ListarFacturasDialog extends JDialog {

	public ListarFacturasDialog(java.awt.Frame owner) {
		super(owner, "Listar facturas", true);

		setSize(500, 400);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());

		JTextArea area = new JTextArea();
		area.setEditable(false);

		List<Factura> facturas = SistemaBDL.getInstance().listarFacturas();

		StringBuilder sb = new StringBuilder();
		for (Factura factura : facturas) {
			sb.append(factura).append("\n");
		}

		area.setText(sb.toString());
		add(new JScrollPane(area), BorderLayout.CENTER);
	}
}
