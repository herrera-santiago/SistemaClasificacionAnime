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
        this.animeService = animeService;
        this.listaService = listaService;
        inicializar();
    }

    private void inicializar() {
        setTitle("Clasificador de Animé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        panelLista = new AnimeListPanel();
        panelAcciones = new AnimeActionsPanel();

        controller = new MainWindowController(this, animeService, listaService);
        panelAcciones.registrarListeners(controller);

        setLayout(new BorderLayout());
        add(panelLista, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);

        refrescarTabla();
    }


    public void mostrar() {
        setVisible(true);
    }

    public void refrescarTabla() {
        if (animeService == null) return;

        try {
            panelLista.actualizarDatos(animeService.obtenerTodos());
        } catch (Exception e) {
            // Si falla la persistencia (archivo vacío/corrupto), mostramos tabla vacía y seguimos
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
