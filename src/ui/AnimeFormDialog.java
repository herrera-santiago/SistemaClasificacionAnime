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
        setSize(560, 520);
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
        listGeneros.setVisibleRowCount(6);

        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JPanel panel = UiTheme.cardLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int row = 0;
        agregarFila(panel, gbc, row++, "Título:", txtTitulo);
        agregarFila(panel, gbc, row++, "Año:", txtAnio);
        agregarFila(panel, gbc, row++, "Capítulos:", txtCapitulos);
        agregarFila(panel, gbc, row++, "Estudio:", comboEstudio);
        agregarFila(panel, gbc, row++, "Estado:", comboEstado);
        agregarFila(panel, gbc, row++, "Calificación:", spinnerCalificacion);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel("Géneros:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        panel.add(new JScrollPane(listGeneros), gbc);

        JButton btnAceptar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        UiTheme.stylePrimaryButton(btnAceptar);
        UiTheme.styleSecondaryButton(btnCancelar);

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
        panelBotones.setOpaque(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        JPanel container = new JPanel(new BorderLayout(0, 10));
        container.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        container.setBackground(UiTheme.BG_APP);
        container.add(panel, BorderLayout.CENTER);
        container.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(container);
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int row, String etiqueta, JComponent componente) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(componente, gbc);
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
