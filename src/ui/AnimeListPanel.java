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

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void actualizarDatos(List<Anime> animes) {
        modeloTabla.setAnimes(animes);
    }

    public int getAnimeSeleccionado() {
        int row = tabla.getSelectedRow();
        if (row == -1) return -1;
        // más adelante: devolver id o índice lógico
        return row;
    }
}
