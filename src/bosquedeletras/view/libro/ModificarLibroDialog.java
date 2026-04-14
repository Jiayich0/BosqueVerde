package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Libro;

public class ModificarLibroDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private FormularioLibroPanel formulario;
    private Libro libro;

    public ModificarLibroDialog(Dialog parent, Libro libro) {
        super(parent, "MODIFICAR LIBRO", true);
        this.libro = libro;

        setSize(520, 320);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        formulario = new FormularioLibroPanel();
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
        formulario.setTitulo(libro.getTitulo());
        formulario.setAutor(libro.getAutor());
        formulario.setIsbn(libro.getIsbn());
        formulario.setEditorial(libro.getEditorial());
        formulario.setAno(String.valueOf(libro.getAno()));
        formulario.setIsbnEditable(false);
    }

    private void guardarCambios() {
        String titulo = formulario.getTitulo();
        String autor = formulario.getAutor();
        String isbn = formulario.getIsbn();
        String editorial = formulario.getEditorial();
        String anoTexto = formulario.getAno();

        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || anoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "DATOS INCOMPLETOS",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(anoTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero.", "DATOS INCORRECTOS",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean modificacionCorrecta = SistemaBDL.getInstance().getControlLibro().modificarLibro(titulo, autor, isbn, editorial, ano);

        if (modificacionCorrecta) {
            JOptionPane.showMessageDialog(this, "Libro modificado correctamente.", "MODIFICACIÓN CORRECTA",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se ha podido modificar el libro.", "ERROR MODIFICACIÓN",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}