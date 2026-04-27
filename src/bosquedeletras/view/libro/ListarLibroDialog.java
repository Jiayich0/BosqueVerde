package bosquedeletras.view.libro;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bosquedeletras.facade.SistemaBDL;
import bosquedeletras.model.Libro;
import bosquedeletras.strategy.SortById;

public class ListarLibroDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextArea areaLibros;

    public ListarLibroDialog(JFrame parent) {
        super(parent, "Listar libros", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        initComponents();
        listarLibros();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        areaLibros = new JTextArea();
        areaLibros.setEditable(false);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(new JScrollPane(areaLibros), BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void listarLibros() {
        List<Libro> libros = SistemaBDL.getInstance()
                .getControlLibro()
                .listarLibros(new SortById<>());

        StringBuilder sb = new StringBuilder();

        for (Libro libro : libros) {
            sb.append(libro).append("\n");
        }

        areaLibros.setText(sb.toString());
    }
}