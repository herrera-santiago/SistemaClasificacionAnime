package service;

import model.Anime;
import model.EstadosAnime;
import model.Generos;

import java.util.List;

public interface IAnimeService {
    public void crearAnime(String titulo, int anioLanzamiento, int cantidadCapitulos, EstadosAnime estado, int calificacion, List<Generos> generos);
    public  List<Anime> ordenarAnimes(List<Anime> animes, CriterioOrdenamiento criterio);
    public List<Anime> filtrar(CriterioFiltrado filtro);

}
