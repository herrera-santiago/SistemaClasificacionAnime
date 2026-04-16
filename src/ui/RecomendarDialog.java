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
        setTitle("★ Recomendador Xeneize");
        setSize(440, 280);
        setLocationRelativeTo(owner);
        init();
    }

    private void init() {
        comboTipo = new JComboBox<>(new String[] { "Top N (global)", "Por género" });

        comboGenero = new JComboBox<>(Generos.values());
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(3, 1, 50, 1));

        JPanel panel = UiTheme.cardLayout(new GridLayout(0, 2, 10, 10));
        panel.add(UiTheme.createCardTitle("Elegir recomendación"));
        panel.add(new JLabel(""));

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
        UiTheme.styleSecondaryButton(btnCancelar);
        UiTheme.stylePrimaryButton(btnOk);

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
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnOk);

        JPanel container = new JPanel(new BorderLayout(0, 10));
        container.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        container.setBackground(UiTheme.BG_APP);
        container.add(panel, BorderLayout.CENTER);
        container.add(botones, BorderLayout.SOUTH);

        setContentPane(container);
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
