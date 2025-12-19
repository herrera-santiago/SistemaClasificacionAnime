package ui;

import model.Anime;
import model.ValidacionException;
import repository.NoEncontradoException;
import service.CriterioFiltrado;
import service.FiltroPorCalificacionMinima;
import service.FiltroPorEstado;
import service.FiltroPorGenero;
import service.IAnimeService;
import service.IListaService;
import service.CriterioRecomendacion;
import service.RecomendacionPorGenero;
import service.RecomendacionTopN;


import javax.swing.JOptionPane;
import java.util.List;

public class MainWindowController {

    private final MainWindow mainWindow;
    private final IAnimeService animeService;
    private final IListaService listaService;

    public MainWindowController(MainWindow mainWindow,
                                IAnimeService animeService,
                                IListaService listaService) {
        this.mainWindow = mainWindow;
        this.animeService = animeService;
        this.listaService = listaService;
    }

    public void onNuevoAnime() {
        try {
            AnimeFormDialog dialog = new AnimeFormDialog(mainWindow);
            AnimeFormDTO dto = dialog.mostrarParaCrear();
            if (dto == null) return; // canceló

            animeService.crearAnime(
                    dto.titulo,
                    dto.anio,
                    dto.estudio,
                    dto.capitulos,
                    dto.estado,
                    dto.calificacion,
                    dto.generos
            );

            mainWindow.refrescarTabla();
            mainWindow.mostrarInfo("Animé creado correctamente.");

        } catch (ValidacionException ex) {
            mainWindow.mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mainWindow.mostrarError("Error al crear animé: " + ex.getMessage());
        }
    }

    public void onEditarAnime() {
        try {
            Anime seleccionado = mainWindow.getPanelLista().getAnimeSeleccionado();
            if (seleccionado == null) {
                mainWindow.mostrarError("Seleccioná un animé para editar.");
                return;
            }

            AnimeFormDialog dialog = new AnimeFormDialog(mainWindow);
            AnimeFormDTO dto = dialog.mostrarParaEditar(seleccionado);
            if (dto == null) return; // canceló

            Anime actualizado = new Anime(
                    dto.titulo,
                    dto.anio,
                    dto.estudio,
                    dto.estado,
                    dto.calificacion,
                    dto.generos,
                    dto.capitulos
            );
            actualizado.setId(seleccionado.getId());

            // IMPORTANTE: esto compila solo si tu amigo ya agregó actualizarAnime(...)
            animeService.actualizarAnime(actualizado);

            mainWindow.refrescarTabla();
            mainWindow.mostrarInfo("Animé actualizado correctamente.");

        } catch (ValidacionException e) {
            mainWindow.mostrarError(e.getMessage());
        } catch (NoEncontradoException e) {
            mainWindow.mostrarError(e.getMessage());
        } catch (Exception e) {
            mainWindow.mostrarError("Error al editar: " + e.getMessage());
        }
    }

    public void onEliminarAnime() {
        try {
            Anime seleccionado = mainWindow.getPanelLista().getAnimeSeleccionado();
            if (seleccionado == null) {
                mainWindow.mostrarError("Seleccioná un animé para eliminar.");
                return;
            }

            int op = JOptionPane.showConfirmDialog(
                    mainWindow,
                    "¿Eliminar el animé: " + seleccionado.getTitulo() + "?",
                    "Confirmar eliminación",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (op != JOptionPane.OK_OPTION) return;

            animeService.eliminarAnime(seleccionado.getTitulo());

            mainWindow.refrescarTabla();
            mainWindow.mostrarInfo("Animé eliminado correctamente.");

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al eliminar: " + ex.getMessage());
        }
    }

    public void onFiltrar() {
        try {
            FiltroAnimeDialog dialog = new FiltroAnimeDialog(mainWindow);
            FiltroDTO dto = dialog.mostrar();
            if (dto == null) return; // canceló

            // Si tu dialog no tiene opción "Todos (null)", genero/estado nunca van a ser null
            CriterioFiltrado criterio = null;

            if (dto.genero != null) {
                criterio = new FiltroPorGenero(dto.genero);
            } else if (dto.estado != null) {
                criterio = new FiltroPorEstado(dto.estado);
            } else {
                criterio = new FiltroPorCalificacionMinima(dto.calificacionMinima);
            }

            List<Anime> filtrados = animeService.filtrar(criterio);
            mainWindow.getPanelLista().actualizarDatos(filtrados);

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al filtrar: " + ex.getMessage());
        }
    }


    public void onLimpiarFiltros() {
        mainWindow.refrescarTabla();
    }

    public void onOrdenar() {
        try {
            OrdenarAnimeDialog dialog = new OrdenarAnimeDialog(mainWindow);
            OrdenarDTO dto = dialog.mostrar();
            if (dto == null) return;

            service.CriterioOrdenamiento criterio;

            switch (dto.campo) {
                case "Título" -> criterio = new service.OrdenarPorTitulo();
                case "Año" -> criterio = new service.OrdenarPorAnio();
                case "Calificación" -> criterio = new service.OrdenarPorCalificaciones();
                case "Género" -> criterio = new service.OrdenarPorGenero();
                default -> throw new IllegalArgumentException("Campo inválido");
            }

            List<Anime> ordenados = animeService.ordenarAnimes(criterio, dto.orden);

            mainWindow.getPanelLista().actualizarDatos(ordenados);

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al ordenar: " + ex.getMessage());
        }
    }

    public void onCantidadPorEstado() {
        try {
            var mapa = animeService.cantidadAnimesPorEstado();

            StringBuilder sb = new StringBuilder("Cantidad de animés por estado:\n\n");
            for (var entry : mapa.entrySet()) {
                sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            mainWindow.mostrarInfo(sb.toString());

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al calcular cantidad por estado: " + ex.getMessage());
        }
    }

    public void onPromedioPorGenero() {
        try {
            var mapa = animeService.obtenerPromedioCalificacionPorGenero();

            StringBuilder sb = new StringBuilder("Promedio de calificación por género:\n\n");
            for (var entry : mapa.entrySet()) {
                sb.append("- ").append(entry.getKey())
                        .append(": ")
                        .append(String.format("%.2f", entry.getValue()))
                        .append("\n");
            }

            mainWindow.mostrarInfo(sb.toString());

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al calcular promedio por género: " + ex.getMessage());
        }
    }

    public void onRecomendar() {
        try {
            RecomendarDialog dialog = new RecomendarDialog(mainWindow);
            RecomendarDTO dto = dialog.mostrar();
            if (dto == null) return;

            CriterioRecomendacion criterio;

            switch (dto.tipo) {
                case "Top N (global)" -> criterio = new RecomendacionTopN();
                case "Por género" -> {
                    if (dto.genero == null) {
                        mainWindow.mostrarError("Seleccioná un género.");
                        return;
                    }
                    criterio = new RecomendacionPorGenero(dto.genero);
                }
                default -> {
                    mainWindow.mostrarError("Tipo inválido.");
                    return;
                }
            }

            List<Anime> recomendados = animeService.recomendar(criterio, dto.cantidad);
            mainWindow.getPanelLista().actualizarDatos(recomendados);
            mainWindow.mostrarInfo("Se mostraron " + recomendados.size() + " recomendados.");

        } catch (Exception ex) {
            mainWindow.mostrarError("Error al recomendar: " + ex.getMessage());
        }
    }

    public void onListas() {
        try {
            Anime seleccionado = mainWindow.getPanelLista().getAnimeSeleccionado(); // puede ser null
            ListasDialog dialog = new ListasDialog(mainWindow, listaService, animeService, seleccionado);
            dialog.setVisible(true);
        } catch (Exception ex) {
            mainWindow.mostrarError("Error al abrir Listas: " + ex.getMessage());
        }
    }

}
