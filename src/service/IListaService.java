package service;

import model.Lista;
import java.util.List;

public interface IListaService {

    Lista crearLista(String nombre);

    void agregarAnimeALista(int idLista, int idAnime);

    void eliminarAnimeDeLista(int idLista, int idAnime);

    List<Lista> obtenerListasQueContienenAnime(int idAnime);

    List<Lista> listarListas();
}
