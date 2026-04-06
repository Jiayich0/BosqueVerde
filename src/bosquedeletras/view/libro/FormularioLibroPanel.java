package bosquedeletras.view.libro;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioLibroPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtAno;

    public FormularioLibroPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        add(txtIsbn);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Año:"));
        txtAno = new JTextField();
        add(txtAno);
    }

    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getAutor() {
        return txtAutor.getText().trim();
    }

    public String getIsbn() {
        return txtIsbn.getText().trim().toUpperCase();
    }

    public String getEditorial() {
        return txtEditorial.getText().trim();
    }

    public String getAno() {
        return txtAno.getText().trim();
    }

    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    public void setAutor(String autor) {
        txtAutor.setText(autor);
    }

    public void setIsbn(String isbn) {
        txtIsbn.setText(isbn);
    }

    public void setEditorial(String editorial) {
        txtEditorial.setText(editorial);
    }

    public void setAno(String ano) {
        txtAno.setText(ano);
    }
}