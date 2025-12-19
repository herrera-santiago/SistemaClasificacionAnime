package ui;

import model.Anime;
import model.Estudios;
import model.EstadosAnime;
import model.Generos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnimeFormDialog extends JDialog {

    private JTextField txtTitulo;
    private JTextField txtAnio;
    private JTextField txtCapitulos;
    private JComboBox<Estudios> comboEstudio;
    private JComboBox<EstadosAnime> comboEstado;
    private JList<Generos> listGeneros;
    private JSpinner spinnerCalificacion;

    private AnimeFormDTO resultado;

    public AnimeFormDialog(Frame owner) {
        super(owner, true);
        setTitle("Formulario Animé");
        setSize(420, 420);
        setLocationRelativeTo(owner);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtTitulo = new JTextField();
        txtAnio = new JTextField();
        txtCapitulos = new JTextField();

        comboEstudio = new JComboBox<>(Estudios.values());
        comboEstado = new JComboBox<>(EstadosAnime.values());

        listGeneros = new JList<>(Generos.values());
        listGeneros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);

        panel.add(new JLabel("Año:"));
        panel.add(txtAnio);

        panel.add(new JLabel("Capítulos:"));
        panel.add(txtCapitulos);

        panel.add(new JLabel("Estudio:"));
        panel.add(comboEstudio);

        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        panel.add(new JLabel("Géneros:"));
        panel.add(new JScrollPane(listGeneros));

        panel.add(new JLabel("Calificación:"));
        panel.add(spinnerCalificacion);


        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            try {
                resultado = obtenerDTO();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Año y Capítulos deben ser números.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            resultado = null;
            dispose();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public AnimeFormDTO mostrarParaCrear() {
        resultado = null;


        txtTitulo.setText("");
        txtAnio.setText("");
        txtCapitulos.setText("");
        comboEstudio.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        listGeneros.clearSelection();
        spinnerCalificacion.setValue(1);

        setVisible(true);
        return resultado;
    }

    public AnimeFormDTO mostrarParaEditar(Anime anime) {
        resultado = null;

        txtTitulo.setText(anime.getTitulo());
        txtAnio.setText(String.valueOf(anime.getAnioLanzamiento()));
        txtCapitulos.setText(String.valueOf(anime.getCantidadCapitulos()));
        comboEstudio.setSelectedItem(anime.getEstudio());
        comboEstado.setSelectedItem(anime.getEstado());
        spinnerCalificacion.setValue(anime.getCalificacionUsuarios());


        List<Generos> gens = anime.getGeneros();
        int[] indices = gens.stream()
                .mapToInt(g -> g.ordinal())
                .toArray();
        listGeneros.setSelectedIndices(indices);

        setVisible(true);
        return resultado;
    }

    private AnimeFormDTO obtenerDTO() {
        List<Generos> generosSeleccionados = listGeneros.getSelectedValuesList();


        return new AnimeFormDTO(
                txtTitulo.getText(),
                Integer.parseInt(txtAnio.getText()),
                (Estudios) comboEstudio.getSelectedItem(),
                Integer.parseInt(txtCapitulos.getText()),
                (EstadosAnime) comboEstado.getSelectedItem(),
                (int) spinnerCalificacion.getValue(),
                generosSeleccionados
        );
    }
}
