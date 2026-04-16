package ui;

import model.Anime;
import model.Lista;
import model.ValidacionException;
import repository.NoEncontradoException;
import service.IAnimeService;
import service.IListaService;
import service.YaExisteException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListasDialog extends JDialog {

    private final IListaService listaService;
    private final IAnimeService animeService;
    private final Anime animeSeleccionado;

    private DefaultListModel<Lista> modelListas;
    private JList<Lista> listListas;

    public ListasDialog(Frame owner, IListaService listaService, IAnimeService animeService, Anime animeSeleccionado) {
        super(owner, true);
        this.listaService = listaService;
        this.animeService = animeService;
        this.animeSeleccionado = animeSeleccionado;

        setTitle("Gestión de Listas");
        setSize(620, 430);
        setLocationRelativeTo(owner);

        initUI();
        refrescarListas();
    }

    private void initUI() {
        modelListas = new DefaultListModel<>();
        listListas = new JList<>(modelListas);
        listListas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listListas.setFixedCellHeight(30);


        listListas.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? "📁 " + value.getNombre() : "");
            lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            if (isSelected) {
                lbl.setOpaque(true);
                lbl.setBackground(list.getSelectionBackground());
                lbl.setForeground(list.getSelectionForeground());
            }
            return lbl;
        });

        JButton btnCrear = new JButton("Crear lista");
        JButton btnAgregarAnime = new JButton("Agregar animé a lista");
        JButton btnVerListasDelAnime = new JButton("Ver listas del animé");
        JButton btnCerrar = new JButton("Cerrar");

        UiTheme.stylePrimaryButton(btnCrear);
        UiTheme.styleSecondaryButton(btnAgregarAnime);
        UiTheme.styleSecondaryButton(btnVerListasDelAnime);
        UiTheme.styleSecondaryButton(btnCerrar);

        btnCrear.addActionListener(e -> crearLista());
        btnAgregarAnime.addActionListener(e -> agregarAnimeALista());
        btnVerListasDelAnime.addActionListener(e -> verListasDelAnime());
        btnCerrar.addActionListener(e -> dispose());


        btnAgregarAnime.setEnabled(animeSeleccionado != null);
        btnVerListasDelAnime.setEnabled(animeSeleccionado != null);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);
        panelBotones.add(btnCrear);
        panelBotones.add(btnAgregarAnime);
        panelBotones.add(btnVerListasDelAnime);
        panelBotones.add(btnCerrar);

        JPanel center = UiTheme.cardLayout(new BorderLayout(0, 10));
        center.add(new JLabel("Listas disponibles"), BorderLayout.NORTH);
        center.add(new JScrollPane(listListas), BorderLayout.CENTER);

        JPanel container = new JPanel(new BorderLayout(0, 10));
        container.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        container.setBackground(UiTheme.BG_APP);
        container.add(center, BorderLayout.CENTER);
        container.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(container);
    }

    private void refrescarListas() {
        try {
            modelListas.clear();
            List<Lista> listas = listaService.listarListas();
            for (Lista l : listas) modelListas.addElement(l);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar listas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearLista() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la lista:");
        if (nombre == null) return;
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            listaService.crearLista(nombre);
            refrescarListas();
            JOptionPane.showMessageDialog(this, "Lista creada.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (ValidacionException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear lista: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarAnimeALista() {
        if (animeSeleccionado == null) return;

        Lista lista = listListas.getSelectedValue();
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            listaService.agregarAnimeALista(lista.getId(), animeSeleccionado.getId());
            JOptionPane.showMessageDialog(this,
                    "Animé agregado a la lista \"" + lista.getNombre() + "\".",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (NoEncontradoException | YaExisteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListasDelAnime() {
        if (animeSeleccionado == null) return;

        try {
            List<Lista> listas = listaService.obtenerListasQueContienenAnime(animeSeleccionado.getId());
            if (listas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El animé no está en ninguna lista.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Lista l : listas) sb.append("• ").append(l.getNombre()).append("\n");

            JOptionPane.showMessageDialog(this, sb.toString(), "Listas que contienen el animé", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
