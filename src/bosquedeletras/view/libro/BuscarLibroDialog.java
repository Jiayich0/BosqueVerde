package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Libro;

public class BuscarLibroDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtIsbn;
    private String accion;

    public BuscarLibroDialog(Frame parent, String accion) {
        super(parent, "BUSCAR LIBRO", true);
        this.accion = accion;

        setSize(350, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.add(new JLabel("ISBN:"));
        txtIsbn = new JTextField(15);
        panelCentro.add(txtIsbn);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");

        btnBuscar.addActionListener(e -> buscar());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnBuscar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscar() {
        String isbn = txtIsbn.getText().trim().toUpperCase();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ISBN es obligatorio.", "DATOS INCOMPLETOS",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro libro = SistemaBDL.getInstance().getControlLibro().leerLibro(isbn);

        if (libro == null) {
            JOptionPane.showMessageDialog(this, "El libro indicado no existe.", "NO EXISTE",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("leer".equals(accion)) {
            LeerLibroDialog dialog = new LeerLibroDialog(this, libro);
            dialog.setVisible(true);
        } else if ("modificar".equals(accion)) {
            ModificarLibroDialog dialog = new ModificarLibroDialog(this, libro);
            dialog.setVisible(true);
        }

        dispose();
    }
}