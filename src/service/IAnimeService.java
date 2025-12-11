package service;

import model.*;

import java.util.List;

public interface IAnimeService {
    public ValidacionException crearAnime(String titulo, int anioLanzamiento, Estudios estudio, int cantidadCapitulos, EstadosAnime estado, int calificacion, List<Generos> generos) throws ValidacionException;
    List<Anime> obtenerTodos(); // la UI necesita ver animes
    void ordenarAnimes(List<Anime> animes, CriterioOrdenamiento criterio, Ordenamientos orden);
    List<Anime> filtrar(CriterioFiltrado filtro);
}
