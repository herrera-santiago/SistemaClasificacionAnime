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
        setSize(500, 350);
        setLocationRelativeTo(owner);

        initUI();
        refrescarListas();
    }

    private void initUI() {
        modelListas = new DefaultListModel<>();
        listListas = new JList<>(modelListas);
        listListas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        listListas.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getNombre() : "");
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

        btnCrear.addActionListener(e -> crearLista());
        btnAgregarAnime.addActionListener(e -> agregarAnimeALista());
        btnVerListasDelAnime.addActionListener(e -> verListasDelAnime());
        btnCerrar.addActionListener(e -> dispose());


        btnAgregarAnime.setEnabled(animeSeleccionado != null);
        btnVerListasDelAnime.setEnabled(animeSeleccionado != null);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCrear);
        panelBotones.add(btnAgregarAnime);
        panelBotones.add(btnVerListasDelAnime);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(listListas), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void refrescarListas() {
        try {
            modelListas.clear();
            List<Lista> listas = listaService.listarListas(); // <-- tu amigo tiene que devolver listas reales
            for (Lista l : listas) modelListas.addElement(l);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar listas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearLista() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la lista:");
        if (nombre == null) return; // canceló
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
            List<Lista> listas = listaService.obtenerListasQueContienenAnime(animeSeleccionado.getId()); // <-- tu amigo debe implementarlo
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
