package ui;

import javax.swing.*;
import java.awt.*;

public class AnimeActionsPanel extends JPanel {

    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnLimpiarFiltro;
    private JButton btnEliminar;
    private JButton btnFiltrar;
    private JButton btnListas;
    private JButton btnOrdenar;
    private JButton btnCantidadPorEstado;
    private JButton btnRecomendar;


    public AnimeActionsPanel() {
        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnLimpiarFiltro = new JButton("Limpiar Filtros");
        btnEliminar = new JButton("Eliminar");
        btnFiltrar = new JButton("Filtrar");
        btnListas = new JButton("Listas");
        btnOrdenar = new JButton("Ordenar");
        btnCantidadPorEstado = new JButton("Cantidad por estado");
        btnRecomendar = new JButton("Recomedar");





        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(btnNuevo);
        add(btnEditar);
        add(btnLimpiarFiltro);
        add(btnEliminar);
        add(btnFiltrar);
        add(btnListas);
        add(btnOrdenar);
        add(btnCantidadPorEstado);
        add(btnRecomendar);

    }

    public void registrarListeners(MainWindowController controlador) {
        btnNuevo.addActionListener(e -> controlador.onNuevoAnime());
        btnEditar.addActionListener(e -> controlador.onEditarAnime());
        btnLimpiarFiltro.addActionListener(e -> controlador.onLimpiarFiltros());
        btnEliminar.addActionListener(e -> controlador.onEliminarAnime());
        btnFiltrar.addActionListener(e -> controlador.onFiltrar());
        btnListas.addActionListener(e -> controlador.onListas());
        btnOrdenar.addActionListener(e -> controlador.onOrdenar());
        btnCantidadPorEstado.addActionListener(e -> controlador.onCantidadPorEstado());
        btnRecomendar.addActionListener(e -> controlador.onRecomendar());


    }
}
