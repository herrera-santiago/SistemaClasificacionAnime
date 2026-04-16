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
        tabla.setGridColor(new Color(210, 223, 255));
        tabla.setSelectionBackground(UiTheme.BOCA_ORO);
        tabla.setSelectionForeground(UiTheme.BOCA_AZUL_OSCURO);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(UiTheme.BOCA_AZUL);
        header.setForeground(UiTheme.BOCA_ORO);
        header.setPreferredSize(new Dimension(0, 36));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(243, 248, 255));
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
        JLabel titulo = UiTheme.createCardTitle("Plantel de animés");
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
