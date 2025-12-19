package service;

import model.*;
import repository.AnimeNoEncontradoException;

import java.util.List;
import java.util.Map;

public interface IAnimeService {

    void crearAnime(
            String titulo,
            int anioLanzamiento,
            Estudios estudio,
            int cantidadCapitulos,
            EstadosAnime estado,
            int calificacion,
            List<Generos> generos
    ) throws ValidacionException;

    List<Anime> obtenerTodos(); // la UI necesita ver animes

    List<Anime> ordenarAnimes(
            List<Anime> animes,
            CriterioOrdenamiento criterio,
            Ordenamientos orden
    );

    List<Anime> filtrar(CriterioFiltrado filtro);

    void actualizarAnime(Anime anime) throws AnimeNoEncontradoException;

    Double obtenerPromedioCalificacionesGlobal();

    List<Generos> top3GenerosMasFrecuentes();

    void eliminarAnime(String titulo);

    Map<EstadosAnime, Integer> cantidadAnimesPorEstado();

    /* List<Anime> recomendar(CriterioRecomendacion criterio, int n);

    cantidadAnimesPorEstado(): Map<EstadoAnime, int>
    obtenerPromedioCalificacionPorGenero(): Map<Generos, double> */

}