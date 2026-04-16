import javax.swing.SwingUtilities;

import repository.AnimeRepositoryArchivo;
import repository.IAnimeRepository;
import repository.IListaRepository;
import repository.ListaRepositoryArchivo;
import service.AnimeServiceImpl;
import service.IAnimeService;
import service.IListaService;
import service.ListaServiceImpl;
import ui.MainWindow;
import ui.UiTheme;

public class Main {
    public static void main(String[] args) {

        IAnimeRepository repo = new AnimeRepositoryArchivo();
        IAnimeService animeService = new AnimeServiceImpl(repo);


        IListaRepository listaRepo = new ListaRepositoryArchivo();
        IListaService listaService = new ListaServiceImpl(listaRepo);

        SwingUtilities.invokeLater(() -> {
            UiTheme.initializeGlobalTheme();
            MainWindow mainWindow = new MainWindow(animeService, listaService);
            mainWindow.mostrar();
        });
    }
}
