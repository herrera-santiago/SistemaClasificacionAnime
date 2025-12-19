import javax.swing.SwingUtilities;

import repository.AnimeRepositoryArchivo;
import repository.IAnimeRepository;
import service.AnimeServiceImpl;
import service.IAnimeService;
import service.IListaService;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {

        IAnimeRepository repo = new AnimeRepositoryArchivo(); // o como se llame
        IAnimeService animeService = new AnimeServiceImpl(repo);

        IListaService listaService = null; // por ahora si no está hecho

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(animeService, listaService);
            mainWindow.mostrar();
        });
    }
}
