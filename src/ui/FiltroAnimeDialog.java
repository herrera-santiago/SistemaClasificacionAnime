package ui;

import model.EstadosAnime;
import model.Generos;

import javax.swing.*;
import java.awt.*;

public class FiltroAnimeDialog extends JDialog {

    private JComboBox<String> comboTipo;
    private JComboBox<Generos> comboGenero;
    private JComboBox<EstadosAnime> comboEstado;
    private JSpinner spinnerCalificacion;

    private FiltroDTO resultado;

    public FiltroAnimeDialog(Frame owner) {
        super(owner, true);
        setTitle("Filtrar Animés");
        setSize(340, 260);
        setLocationRelativeTo(owner);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        comboTipo = new JComboBox<>(new String[]{"Género", "Estado", "Calificación mínima"});

        comboGenero = new JComboBox<>(Generos.values());
        comboEstado = new JComboBox<>(EstadosAnime.values());
        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Filtrar por:"));
        panel.add(comboTipo);

        panel.add(new JLabel("Género:"));
        panel.add(comboGenero);

        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        panel.add(new JLabel("Calificación mínima:"));
        panel.add(spinnerCalificacion);


        comboTipo.addActionListener(e -> actualizarHabilitados());
        actualizarHabilitados();

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnAplicar = new JButton("Aplicar");

        btnCancelar.addActionListener(e -> {
            resultado = null;
            dispose();
        });

        btnAplicar.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            if ("Género".equals(tipo)) {
                resultado = new FiltroDTO((Generos) comboGenero.getSelectedItem(), null, 0);
            } else if ("Estado".equals(tipo)) {
                resultado = new FiltroDTO(null, (EstadosAnime) comboEstado.getSelectedItem(), 0);
            } else {
                resultado = new FiltroDTO(null, null, (int) spinnerCalificacion.getValue());
            }
            dispose();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAplicar);

        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarHabilitados() {
        String tipo = (String) comboTipo.getSelectedItem();

        boolean porGenero = "Género".equals(tipo);
        boolean porEstado = "Estado".equals(tipo);
        boolean porCalif = "Calificación mínima".equals(tipo);

        comboGenero.setEnabled(porGenero);
        comboEstado.setEnabled(porEstado);
        spinnerCalificacion.setEnabled(porCalif);
    }

    public FiltroDTO mostrar() {
        resultado = null;
        setVisible(true);
        return resultado;
    }
}
