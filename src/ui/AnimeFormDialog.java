package ui;

import model.Anime;
import model.EstadosAnime;
import model.Generos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnimeFormDialog extends JDialog {

    private JTextField txtTitulo;
    private JTextField txtAnio;
    private JTextField txtCapitulos;
    private JComboBox<EstadosAnime> comboEstado;
    private JList<Generos> listGeneros;
    private JSpinner spinnerCalificacion;

    public AnimeFormDialog(Frame owner) {
        super(owner, true);
        setTitle("Formulario Animé");
        setSize(400, 400);
        setLocationRelativeTo(owner);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtTitulo = new JTextField();
        txtAnio = new JTextField();
        txtCapitulos = new JTextField();

        comboEstado = new JComboBox<>(EstadosAnime.values());
        listGeneros = new JList<>(Generos.values());

        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);

        panel.add(new JLabel("Año:"));
        panel.add(txtAnio);

        panel.add(new JLabel("Capítulos:"));
        panel.add(txtCapitulos);

        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        panel.add(new JLabel("Géneros:"));
        panel.add(new JScrollPane(listGeneros));

        panel.add(new JLabel("Calificación:"));
        panel.add(spinnerCalificacion);

        add(panel, BorderLayout.CENTER);
    }

    public AnimeFormDTO mostrarParaCrear() {
        int opcion = JOptionPane.showConfirmDialog(
                this, "Confirmar", "Crear Animé",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION)
            return null;

        return obtenerDTO();
    }

    public AnimeFormDTO mostrarParaEditar(Anime anime) {
        // después lo completás con la carga de datos del anime
        return mostrarParaCrear();
    }

    private AnimeFormDTO obtenerDTO() {
        List<Generos> generosSeleccionados = listGeneros.getSelectedValuesList();

        return new AnimeFormDTO(
                txtTitulo.getText(),
                Integer.parseInt(txtAnio.getText()),
                Integer.parseInt(txtCapitulos.getText()),
                (EstadosAnime) comboEstado.getSelectedItem(),
                (int) spinnerCalificacion.getValue(),
                generosSeleccionados
        );
    }
}
