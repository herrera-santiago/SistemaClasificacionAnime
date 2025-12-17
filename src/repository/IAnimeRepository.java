package repository;

import model.Anime;
import model.Generos;

import java.util.List;

public interface IAnimeRepository {

    void guardarAnime(Anime nuevoAnime);

    List<Anime> obtenerAnimesPorTitulo(String titulo);

    List<Anime> obtenerAnimesPorRangoAnios(int anioDesde, int anioHasta);

    List<Anime> obtenerAnimesPorGenero(Generos genero);

    Anime obtenerAnimePorId(int id) throws AnimeNoEncontradoException;

    void eliminarAnime(String titulo);

    void actualizarAnime(Anime anime) throws AnimeNoEncontradoException;

    List<Anime> listarAnimes();

}
