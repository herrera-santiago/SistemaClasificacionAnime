package ui;

import model.Ordenamientos;

import javax.swing.*;
import java.awt.*;

public class OrdenarAnimeDialog extends JDialog {

    private JComboBox<String> comboCampo;
    private JComboBox<Ordenamientos> comboOrden;
    private OrdenarDTO resultado;

    public OrdenarAnimeDialog(Frame owner) {
        super(owner, true);
        setTitle("Ordenar animés");
        setSize(420, 250);
        setLocationRelativeTo(owner);
        inicializar();
    }

    private void inicializar() {
        comboCampo = new JComboBox<>(new String[]{"Título", "Año", "Calificación", "Género"});
        comboOrden = new JComboBox<>(Ordenamientos.values());

        JPanel panel = UiTheme.cardLayout(new GridLayout(0, 2, 10, 10));
        panel.add(new JLabel("Ordenar por:"));
        panel.add(comboCampo);

        panel.add(new JLabel("Orden:"));
        panel.add(comboOrden);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnAplicar = new JButton("Aplicar");
        UiTheme.styleSecondaryButton(btnCancelar);
        UiTheme.stylePrimaryButton(btnAplicar);

        btnCancelar.addActionListener(e -> { resultado = null; dispose(); });
        btnAplicar.addActionListener(e -> {
            resultado = new OrdenarDTO(
                    (String) comboCampo.getSelectedItem(),
                    (Ordenamientos) comboOrden.getSelectedItem()
            );
            dispose();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAplicar);

        JPanel container = new JPanel(new BorderLayout(0, 10));
        container.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        container.setBackground(UiTheme.BG_APP);
        container.add(panel, BorderLayout.CENTER);
        container.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(container);
    }

    public OrdenarDTO mostrar() {
        resultado = null;
        setVisible(true);
        return resultado;
    }
}
