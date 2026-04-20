package bosquedeletras.view.editorial;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Editorial;

public class ModificarEditorialDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private FormularioEditorialPanel formulario;
    private Editorial editorial;

    public ModificarEditorialDialog(Dialog parent, Editorial editorial) {
        super(parent, "MODIFICAR EDITORIAL", true);
        this.editorial = editorial;

        setSize(400, 220);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        formulario = new FormularioEditorialPanel();
        add(formulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarCambios());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        formulario.setId(editorial.getId());
        formulario.setNombre(editorial.getNombre());
        formulario.setIdEditable(false);  // El ID no se puede modificar
    }

    private void guardarCambios() {
        String id = formulario.getId();
        String nombre = formulario.getNombre();

        // Validar nuevos datos
        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "ID Editorial y Nombre son obligatorios.", 
                "DATOS INCOMPLETOS",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Modificar datos de editorial en BBDD
        boolean modificacionCorrecta = SistemaBDL.getInstance().getControlEditorial()
                .modificarEditorial(id, nombre);

        if (modificacionCorrecta) {
            JOptionPane.showMessageDialog(this, 
                "Editorial modificada correctamente.", 
                "MODIFICACIÓN CORRECTA",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se ha podido modificar la editorial.", 
                "ERROR MODIFICACIÓN",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}