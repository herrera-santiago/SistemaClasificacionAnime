package ui;

import model.Generos;

import javax.swing.*;
import java.awt.*;

public class RecomendarDialog extends JDialog {

    private JComboBox<String> comboTipo;
    private JComboBox<Generos> comboGenero;
    private JSpinner spinnerCantidad;
    private RecomendarDTO resultado;

    public RecomendarDialog(Frame owner) {
        super(owner, true);
        setTitle("Recomendar animés");
        setSize(360, 220);
        setLocationRelativeTo(owner);
        init();
    }

    private void init() {
        comboTipo = new JComboBox<>(new String[] { "Top N (global)", "Por género" });

        comboGenero = new JComboBox<>(Generos.values());
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(3, 1, 50, 1));

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Tipo:"));
        panel.add(comboTipo);

        panel.add(new JLabel("Género:"));
        panel.add(comboGenero);

        panel.add(new JLabel("Cantidad (n):"));
        panel.add(spinnerCantidad);

        comboTipo.addActionListener(e -> actualizarHabilitados());
        actualizarHabilitados();

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnOk = new JButton("Recomendar");

        btnCancelar.addActionListener(e -> { resultado = null; dispose(); });
        btnOk.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            int n = (int) spinnerCantidad.getValue();

            Generos genero = null;
            if ("Por género".equals(tipo)) {
                genero = (Generos) comboGenero.getSelectedItem();
            }

            resultado = new RecomendarDTO(tipo, n, genero);
            dispose();
        });

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(btnCancelar);
        botones.add(btnOk);

        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private void actualizarHabilitados() {
        String tipo = (String) comboTipo.getSelectedItem();
        comboGenero.setEnabled("Por género".equals(tipo));
    }

    public RecomendarDTO mostrar() {
        resultado = null;
        setVisible(true);
        return resultado;
    }
}
