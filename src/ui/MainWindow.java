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
    }

    public void mostrar() {
        setVisible(true);
    }

    public void refrescarTabla() {
        // más adelante: pedir lista al service y actualizar panelLista
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
