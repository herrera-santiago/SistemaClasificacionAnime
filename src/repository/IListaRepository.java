package repository;

import model.Anime;
import model.Lista;

import java.util.List;

public interface IListaRepository {
    void guardarLista(Lista nuevaLista);
    void actualizarLista(Lista lista) throws NoEncontradoException;
    void eliminarLista (String nombre);
    List<Lista> listarListas();
    List<Lista> obtenerListaPorNombre(String nombre);
    Lista obtenerListaPorId(int id) throws NoEncontradoException;
}
