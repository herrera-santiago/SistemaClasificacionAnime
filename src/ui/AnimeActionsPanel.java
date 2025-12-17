package ui;

import javax.swing.*;
import java.awt.*;

public class AnimeActionsPanel extends JPanel {

    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnFiltrar;
    private JButton btnListas;

    public AnimeActionsPanel() {
        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnFiltrar = new JButton("Filtrar");
        btnListas = new JButton("Listas");

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(btnNuevo);
        add(btnEditar);
        add(btnEliminar);
        add(btnFiltrar);
        add(btnListas);
    }

    public void registrarListeners(MainWindowController controlador) {
        btnNuevo.addActionListener(e -> controlador.onNuevoAnime());
        btnEditar.addActionListener(e -> controlador.onEditarAnime());
        btnEliminar.addActionListener(e -> controlador.onEliminarAnime());
        btnFiltrar.addActionListener(e -> controlador.onFiltrar());
        btnListas.addActionListener(e -> controlador.onListas());
    }
}
