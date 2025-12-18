package ui;

import model.Anime;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnimeListPanel extends JPanel {

    private JTable tabla;
    private AnimeTableModel modeloTabla;

    public AnimeListPanel() {
        setLayout(new BorderLayout());

        modeloTabla = new AnimeTableModel();
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void actualizarDatos(List<Anime> animes) {
        modeloTabla.setAnimes(animes);
    }

    // Devuelve el Anime seleccionado o null si no hay selección
    public Anime getAnimeSeleccionado() {
        int row = tabla.getSelectedRow();
        if (row == -1) return null;
        return modeloTabla.getAnimeAt(row);
    }
}
