package ui;

import model.EstadosAnime;
import model.Generos;

import javax.swing.*;
import java.awt.*;

public class FiltroAnimeDialog extends JDialog {

    private JComboBox<Generos> comboGenero;
    private JComboBox<EstadosAnime> comboEstado;
    private JSpinner spinnerCalificacion;

    public FiltroAnimeDialog(Frame owner) {
        super(owner, true);
        setTitle("Filtrar Animés");
        setSize(300, 300);
        setLocationRelativeTo(owner);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        comboGenero = new JComboBox<>(Generos.values());
        comboEstado = new JComboBox<>(EstadosAnime.values());
        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Género:"));
        panel.add(comboGenero);

        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        panel.add(new JLabel("Calificación mínima:"));
        panel.add(spinnerCalificacion);

        add(panel, BorderLayout.CENTER);
    }

    public FiltroDTO mostrar() {
        int opcion = JOptionPane.showConfirmDialog(
                this, "Aplicar filtro?", "Filtro",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION)
            return null;

        return new FiltroDTO(
                (Generos) comboGenero.getSelectedItem(),
                (EstadosAnime) comboEstado.getSelectedItem(),
                (int) spinnerCalificacion.getValue()
        );
    }
}
