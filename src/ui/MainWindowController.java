package ui;

import service.IAnimeService;
import service.IListaService;

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
        // después: abrir AnimeFormDialog, llamar a animeService.crearAnime(...)
    }

    public void onEditarAnime() {
        // después
    }

    public void onEliminarAnime() {
        // después
    }

    public void onFiltrar() {
        // después
    }

    public void onListas() {
        // después
    }
}
