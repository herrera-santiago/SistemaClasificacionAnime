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
        setSize(340, 200);
        setLocationRelativeTo(owner);
        inicializar();
    }

    private void inicializar() {
        comboCampo = new JComboBox<>(new String[]{"Título", "Año", "Calificación", "Género"});
        comboOrden = new JComboBox<>(Ordenamientos.values());

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Ordenar por:"));
        panel.add(comboCampo);

        panel.add(new JLabel("Orden:"));
        panel.add(comboOrden);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnAplicar = new JButton("Aplicar");

        btnCancelar.addActionListener(e -> { resultado = null; dispose(); });
        btnAplicar.addActionListener(e -> {
            resultado = new OrdenarDTO(
                    (String) comboCampo.getSelectedItem(),
                    (Ordenamientos) comboOrden.getSelectedItem()
            );
            dispose();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAplicar);

        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public OrdenarDTO mostrar() {
        resultado = null;
        setVisible(true);
        return resultado;
    }
}
