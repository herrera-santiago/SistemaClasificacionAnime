package ui;

import service.IAnimeService;
import service.IListaService;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final IAnimeService animeService;
    private final IListaService listaService;

    private AnimeListPanel panelLista;
    private AnimeActionsPanel panelAcciones;
    private MainWindowController controller;

    public MainWindow(IAnimeService animeService, IListaService listaService) {
        UiTheme.initializeGlobalTheme();
        this.animeService = animeService;
        this.listaService = listaService;
        inicializar();
    }

    private void inicializar() {
        setTitle("Clasificador de Animé · Edición Xeneize");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1240, 760);
        setMinimumSize(new Dimension(980, 620));
        setLocationRelativeTo(null);
        getContentPane().setBackground(UiTheme.BG_APP);

        panelLista = new AnimeListPanel();
        panelAcciones = new AnimeActionsPanel();

        controller = new MainWindowController(this, animeService, listaService);
        panelAcciones.registrarListeners(controller);

        setLayout(new BorderLayout(14, 14));
        add(crearHeader(), BorderLayout.NORTH);
        add(panelLista, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.EAST);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        refrescarTabla();
    }

    private JComponent crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(true);
        header.setBackground(UiTheme.BOCA_AZUL_OSCURO);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.BOCA_AZUL),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel titulo = new JLabel("💙💛 Gestión de Animé · Theme Boca Juniors");
        titulo.setFont(((Font) UIManager.get("App.titleFont")));
        titulo.setForeground(UiTheme.BOCA_ORO);

        JLabel subtitulo = new JLabel("Dale Bo, administra, filtra y recomienda con estilo xeneize.");
        subtitulo.setForeground(new Color(229, 237, 255));

        JPanel textWrap = new JPanel(new GridLayout(0, 1, 0, 6));
        textWrap.setOpaque(false);
        textWrap.add(titulo);
        textWrap.add(subtitulo);

        JPanel franjaOro = new JPanel();
        franjaOro.setBackground(UiTheme.BOCA_ORO);
        franjaOro.setPreferredSize(new Dimension(8, 0));

        header.add(textWrap, BorderLayout.CENTER);
        header.add(franjaOro, BorderLayout.EAST);
        return header;
    }

    public void mostrar() {
        setVisible(true);
    }

    public void refrescarTabla() {
        if (animeService == null) return;

        try {
            panelLista.actualizarDatos(animeService.obtenerTodos());
        } catch (Exception e) {
            panelLista.actualizarDatos(java.util.Collections.emptyList());
            mostrarError("No se pudieron cargar los animés desde archivo. Se inicia vacío.\n" + e.getMessage());
        }
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public AnimeListPanel getPanelLista() {
        return panelLista;
    }
}
