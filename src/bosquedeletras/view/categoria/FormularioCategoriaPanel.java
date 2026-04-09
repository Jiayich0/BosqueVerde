package bosquedeletras.view.categoria;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioCategoriaPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre;
    private JTextField txtDescripcion;

    public FormularioCategoriaPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setDescripcion(String descripcion) {
        txtDescripcion.setText(descripcion);
    }
}