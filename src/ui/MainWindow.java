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
        setTitle("Clasificador de Animé · Pro UI");
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
        header.setBackground(UiTheme.BG_CARD);
        header.setBorder(UiTheme.cardBorder());

        JLabel titulo = new JLabel("Panel de Gestión de Animé");
        titulo.setFont(((Font) UIManager.get("App.titleFont")));
        titulo.setForeground(UiTheme.TEXT_MAIN);

        JLabel subtitulo = new JLabel("Administra, filtra, ordena y recomienda con una experiencia moderna.");
        subtitulo.setForeground(UiTheme.TEXT_MUTED);

        JPanel textWrap = new JPanel(new GridLayout(0, 1, 0, 6));
        textWrap.setOpaque(false);
        textWrap.add(titulo);
        textWrap.add(subtitulo);

        header.add(textWrap, BorderLayout.WEST);
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
