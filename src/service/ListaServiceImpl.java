package service;

import model.Lista;
import model.ValidacionException;
import repository.IListaRepository;
import repository.NoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class ListaServiceImpl implements IListaService{
    private final IListaRepository listaRepository;

    public ListaServiceImpl(IListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    @Override
    public void crearLista(String nombre) throws ValidacionException {
        if (!listaRepository.obtenerListaPorNombre(nombre).isEmpty()) {
            throw new YaExisteException("Lista" + nombre + ", ya existe");
        }
        Lista nuevaLista = new Lista(nombre, new ArrayList<>());
        listaRepository.guardarLista(nuevaLista);
    }

    @Override
    public void agregarAnimeALista(int idLista, int idAnime) throws NoEncontradoException, YaExisteException {
        Lista lista = listaRepository.obtenerListaPorId(idLista);
        lista.agregarAnime(idAnime);
        listaRepository.actualizarLista(lista);
    }

    @Override
    public void eliminarAnimeDeLista(int idLista, int idAnime) {

    }

    @Override
    public List<Lista> obtenerListasQueContienenAnime(int idAnime) {
        return List.of();
    }

    @Override
    public List<Lista> listarListas() {
        return List.of();
    }
}
