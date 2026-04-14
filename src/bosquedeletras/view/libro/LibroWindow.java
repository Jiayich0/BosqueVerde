package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LibroWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public LibroWindow() {
        setTitle("LIBRO");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAlta = crearBotonSubsistema("ALTA LIBRO");
        JButton btnModificar = crearBotonSubsistema("MODIFICAR LIBRO");
        JButton btnLeer = crearBotonSubsistema("LEER LIBRO");
        JButton btnVolver = crearBotonSubsistema("VOLVER");

        btnAlta.addActionListener(e -> abrirAltaLibro());
        btnModificar.addActionListener(e -> abrirModificarLibro());
        btnLeer.addActionListener(e -> abrirLeerLibro());
        btnVolver.addActionListener(e -> dispose());

        panelBotones.add(btnAlta);
        panelBotones.add(btnModificar);
        panelBotones.add(btnLeer);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.CENTER);
    }

    private JButton crearBotonSubsistema(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        return boton;
    }

    private void abrirAltaLibro() {
        AltaLibroDialog dialog = new AltaLibroDialog(this);
        dialog.setVisible(true);
    }

    private void abrirModificarLibro() {
        BuscarLibroDialog dialog = new BuscarLibroDialog(this, "modificar");
        dialog.setVisible(true);
    }

    private void abrirLeerLibro() {
        BuscarLibroDialog dialog = new BuscarLibroDialog(this, "leer");
        dialog.setVisible(true);
    }
}