package service;

import model.*;
import repository.IAnimeRepository;

import java.util.ArrayList;
import java.util.List;

public class AnimeServiceImpl implements IAnimeService {
    private final IAnimeRepository animeRepository;

    public AnimeServiceImpl(IAnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

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
        if (!animeRepository.obtenerAnimesPorTitulo(titulo).isEmpty()) {
            throw new AnimeYaExisteException("Anime " + titulo + ", ya existe");
        }
        Anime nuevoAnime = new Anime(titulo,
                anioLanzamiento,
                estudio,
                estado,
                calificacion,
                generos,
                cantidadCapitulos);
        animeRepository.guardarAnime(nuevoAnime);
    }

    @Override
    public List<Anime> obtenerTodos() {
        return this.animeRepository.listarAnimes();
    }

    @Override
    public List<Anime> ordenarAnimes(List<Anime> animes, CriterioOrdenamiento criterio, Ordenamientos orden) {
        return criterio.ordenar(this.animeRepository.listarAnimes(), orden);
    }

    @Override
    public List<Anime> filtrar(CriterioFiltrado criterio) {
        return criterio.filtrar(this.animeRepository.listarAnimes());
    }

    @Override
    public Double obtenerPromedioCalificacionesGlobal() {
        List<Anime> animes = this.animeRepository.listarAnimes();
        int sumaCalificacionesTotal = 0;

        for (int i = 0; i <= animes.size() ; i++) {
            sumaCalificacionesTotal += animes.get(i).getCalificacionUsuarios();
        }
        return (double) (sumaCalificacionesTotal / animes.size());
    }

    @Override
    public List<Generos> top3GenerosMasFrecuentes() {
        Generos[] generos = Generos.values();
        List<Integer> cantidades = new ArrayList<>();

        for (Generos genero : generos) {
            int amount = this.animeRepository.obtenerAnimesPorGenero(genero).size();
            cantidades.add(amount);
        }

        List<Generos> top3 = new ArrayList<>();

        for (int k = 0; k < 3 && k < generos.length; k++) {
            int maxIndex = -1;
            int maxCantidad = -1;
            for (int i = 0; i < cantidades.size(); i++) {
                if (cantidades.get(i) > maxCantidad) {
                    maxCantidad = cantidades.get(i);
                    maxIndex = i;
                }
            }
            if (maxIndex != -1 && maxCantidad > 0) {
                top3.add(generos[maxIndex]);
                cantidades.set(maxIndex, -1);
            }
        }
        return top3;
    }

    @Override
    public List<Anime> recomendar(CriterioRecomendacion criterio, int n) {
        return criterio.recomendar(n);
    }

}
