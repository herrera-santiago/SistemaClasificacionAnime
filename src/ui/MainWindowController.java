package ui;

import model.Anime;
import model.ValidacionException;
import repository.AnimeNoEncontradoException;
import service.CriterioFiltrado;
import service.FiltroPorCalificacionMinima;
import service.FiltroPorEstado;
import service.FiltroPorGenero;
import service.IAnimeService;
import service.IListaService;

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
        } catch (AnimeNoEncontradoException e) {
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

    public void onListas() {
        mainWindow.mostrarInfo("Listas: pendiente.");
    }
}
