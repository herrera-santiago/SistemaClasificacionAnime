package ui;

import model.Anime;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AnimeTableModel extends AbstractTableModel {

    private List<Anime> animes = new ArrayList<>();
    private final String[] columnas = {"Título", "Año", "Capítulos", "Estado", "Calificación"};

    public void setAnimes(List<Anime> animes) {
        this.animes = (animes != null) ? animes : new ArrayList<>();
        fireTableDataChanged();
    }

    public Anime getAnimeAt(int row) {
        if (row < 0 || row >= animes.size()) return null;
        return animes.get(row);
    }

    @Override
    public int getRowCount() {
        return animes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Anime a = animes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> a.getTitulo();
            case 1 -> a.getAnioLanzamiento();
            case 2 -> a.getCantidadCapitulos();
            case 3 -> a.getEstado();
            case 4 -> a.getCalificacionUsuarios();
            default -> "";
        };
    }
}
