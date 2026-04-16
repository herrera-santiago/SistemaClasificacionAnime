package ui;

import model.Anime;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class AnimeListPanel extends JPanel {

    private JTable tabla;
    private AnimeTableModel modeloTabla;

    public AnimeListPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        modeloTabla = new AnimeTableModel();
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);
        tabla.setRowHeight(32);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(UiTheme.BORDER);
        tabla.setSelectionBackground(new Color(221, 235, 255));
        tabla.setSelectionForeground(UiTheme.TEXT_MAIN);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(237, 242, 255));
        header.setForeground(UiTheme.TEXT_MAIN);
        header.setPreferredSize(new Dimension(0, 36));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 255));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return c;
            }
        };

        for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JPanel card = UiTheme.cardLayout(new BorderLayout(0, 10));
        JLabel titulo = new JLabel("Listado de animés");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 18f));
        card.add(titulo, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);

        add(card, BorderLayout.CENTER);
    }

    public void actualizarDatos(List<Anime> animes) {
        modeloTabla.setAnimes(animes);
    }

    public Anime getAnimeSeleccionado() {
        int viewRow = tabla.getSelectedRow();
        if (viewRow == -1) return null;
        int modelRow = tabla.convertRowIndexToModel(viewRow);
        return modeloTabla.getAnimeAt(modelRow);
    }

}
