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
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel card = UiTheme.cardLayout(new BorderLayout(0, 12));

        JLabel titulo = UiTheme.createCardTitle("Acciones xeneizes");
        card.add(titulo, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 0, 8));
        botones.setOpaque(false);

        btnNuevo = new JButton("⚽ Nuevo animé");
        btnEditar = new JButton("✎ Editar seleccionado");
        btnEliminar = new JButton("🗑 Eliminar seleccionado");
        btnFiltrar = new JButton("🔎 Filtrar plantel");
        btnLimpiarFiltro = new JButton("⟲ Limpiar filtros");
        btnOrdenar = new JButton("⇅ Ordenar listado");
        btnRecomendar = new JButton("★ Recomendar");
        btnListas = new JButton("☰ Gestionar listas");
        btnCantidadPorEstado = new JButton("📊 Cantidad por estado");

        UiTheme.stylePrimaryButton(btnNuevo);
        UiTheme.styleSecondaryButton(btnEditar);
        UiTheme.styleDangerButton(btnEliminar);
        UiTheme.styleSecondaryButton(btnFiltrar);
        UiTheme.styleSecondaryButton(btnLimpiarFiltro);
        UiTheme.styleSecondaryButton(btnOrdenar);
        UiTheme.styleSecondaryButton(btnRecomendar);
        UiTheme.styleSecondaryButton(btnListas);
        UiTheme.styleSecondaryButton(btnCantidadPorEstado);

        botones.add(btnNuevo);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        botones.add(new JSeparator());
        botones.add(btnFiltrar);
        botones.add(btnLimpiarFiltro);
        botones.add(btnOrdenar);
        botones.add(btnRecomendar);
        botones.add(btnListas);
        botones.add(btnCantidadPorEstado);

        card.add(botones, BorderLayout.CENTER);
        add(card, BorderLayout.NORTH);
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
