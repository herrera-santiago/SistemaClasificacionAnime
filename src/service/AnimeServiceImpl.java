package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class AnimeServiceImpl implements IAnimeService {
    List<Anime> animes = new ArrayList<>();

    @Override
    public ValidacionException crearAnime(
            String titulo,
            int anioLanzamiento,
            Estudios estudio,
            int cantidadCapitulos,
            EstadosAnime estado,
            int calificacion,
            List<Generos> generos
    ) throws ValidacionException {
        Anime nuevoAnime = new Anime(titulo, anioLanzamiento, estudio, estado, calificacion, generos, cantidadCapitulos);
        animes.add(nuevoAnime);
        // llamar al repository aca para guardar el nuevo anime
        return null;
    }

    @Override
    public List<Anime> obtenerTodos() {
        // deberia obtener todos los animes del repository
        return animes;
    }

    @Override
    public void ordenarAnimes(List<Anime> animes, CriterioOrdenamiento criterio, Ordenamientos orden) {
        criterio.ordenar(this.animes, orden);
    }

    @Override
    public List<Anime> filtrar(CriterioFiltrado criterio) {
        return criterio.filtrar(this.animes);
    }
}
