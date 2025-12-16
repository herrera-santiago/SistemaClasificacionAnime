package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class AnimeServiceImpl implements IAnimeService {
    List<Anime> animes = new ArrayList<>();

    @Override
    public void crearAnime(
            String titulo,
            int anioLanzamiento,
            Estudios estudio,
            int cantidadCapitulos,
            EstadosAnime estado,
            int calificacion,
            List<Generos> generos
    ) throws ValidacionException {
        Anime nuevoAnime = new Anime(titulo,
                anioLanzamiento,
                estudio,
                estado,
                calificacion,
                generos,
                cantidadCapitulos);
        animes.add(nuevoAnime);
        // llamar al repository aca para guardar el nuevo anime
    }

    @Override
    public List<Anime> obtenerTodos() {
        // deberia obtener todos los animes del repository
        return animes;
    }

    @Override
    public List<Anime> ordenarAnimes(List<Anime> animes, CriterioOrdenamiento criterio, Ordenamientos orden) {
        return criterio.ordenar(this.animes, orden);
    }

    @Override
    public List<Anime> filtrar(CriterioFiltrado criterio) {
        return criterio.filtrar(this.animes);
    }
}
