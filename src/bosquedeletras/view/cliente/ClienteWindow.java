package bosquedeletras.view.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bosquedeletras.view.MainWindow;

public class ClienteWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public ClienteWindow() {
		setTitle("CLIENTE");
		setSize(360, 460);
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
		contenedor.setBorder(BorderFactory.createEmptyBorder(30, 72, 0, 72));

		JPanel panel = new JPanel(new GridLayout(7, 1, 0, 12));

		JButton btnAlta = crearBoton("ALTA CLIENTE");
		JButton btnBaja = crearBoton("BAJA CLIENTE");
		JButton btnModificar = crearBoton("MODIFICAR CLIENTE");
		JButton btnLeer = crearBoton("LEER CLIENTE");
		JButton btnListar = crearBoton("LISTAR CLIENTES");
		JButton btnRegistroSocio = crearBoton("REGISTRAR SOCIO");
		JButton btnBajaSocio = crearBoton("BAJA SOCIO");

		btnAlta.addActionListener(e -> abrirAlta());
		btnBaja.addActionListener(e -> abrirBaja());
		btnModificar.addActionListener(e -> abrirModificar());
		btnLeer.addActionListener(e -> abrirLeer());
		btnListar.addActionListener(e -> abrirListar());
		btnRegistroSocio.addActionListener(e -> abrirRegistrarSocio());
		btnBajaSocio.addActionListener(e -> abrirBajaSocio());

		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnLeer);
		panel.add(btnListar);
		panel.add(btnRegistroSocio);
		panel.add(btnBajaSocio);

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

	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setPreferredSize(new Dimension(60, 36));
		boton.setFont(new Font("SansSerif", Font.BOLD, 13));
		return boton;
	}

	private void abrirAlta() {
		new AltaClienteDialog(this).setVisible(true);
	}

	private void abrirBaja() {
		new BajaClienteDialog(this).setVisible(true);
	}

	private void abrirModificar() {
		new BuscarClienteDialog(this, "modificar").setVisible(true);
	}

	private void abrirLeer() {
		new BuscarClienteDialog(this, "leer").setVisible(true);
	}

	private void abrirListar() {
		new ListarClientesDialog(this).setVisible(true);
	}

	private void abrirRegistrarSocio() {
		new RegistrarSocioDialog(this).setVisible(true);
	}

	private void abrirBajaSocio() {
		new DarBajaSocioDialog(this).setVisible(true);
	}

	private void volverMenuPrincipal() {
		new MainWindow().setVisible(true);
		dispose();
	}
}
