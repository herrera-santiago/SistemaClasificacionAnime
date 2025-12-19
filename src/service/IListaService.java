package service;

import model.Lista;
import model.ValidacionException;
import repository.NoEncontradoException;

import java.util.List;

public interface IListaService {

    void crearLista(String nombre) throws ValidacionException;

    void agregarAnimeALista(int idLista, int idAnime) throws NoEncontradoException, YaExisteException;

    void eliminarAnimeDeLista(int idLista, int idAnime);

    List<Lista> obtenerListasQueContienenAnime(int idAnime);

    List<Lista> listarListas();
}
