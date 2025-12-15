import javax.swing.*;
import ui.MainWindow;
import service.IAnimeService;
import service.IListaService;

public class Main {
    public static void main(String[] args) {

        // Por ahora no usamos los servicios reales
        IAnimeService animeService = null;
        IListaService listaService = null;

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(animeService, listaService);
            mainWindow.mostrar();
        });
    }
}
