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
        if (!this.listaRepository.obtenerListaPorNombre(nombre).isEmpty()) {
            throw new YaExisteException("Lista" + nombre + ", ya existe");
        }
        Lista nuevaLista = new Lista(nombre, new ArrayList<>());
        this.listaRepository.guardarLista(nuevaLista);
    }

    @Override
    public void agregarAnimeALista(int idLista, int idAnime) throws NoEncontradoException, YaExisteException {
        Lista lista = this.listaRepository.obtenerListaPorId(idLista);
        lista.agregarAnime(idAnime);
        this.listaRepository.actualizarLista(lista);
    }

    @Override
    public void eliminarAnimeDeLista(int idLista, int idAnime) throws NoEncontradoException {
        Lista lista = this.listaRepository.obtenerListaPorId(idLista);
        if (lista.existeAnime(idAnime)) {
            lista.getAnimesIds().removeIf(id -> id.equals(idAnime));
            return;
        }
        throw new NoEncontradoException("Anime " + idAnime + ", no existe en la lista");
    }

    @Override
    public void eliminarLista(String nombre) {
        this.listaRepository.eliminarLista(nombre);
    }

    @Override
    public List<Lista> obtenerListasQueContienenAnime(int idAnime) {
        List<Lista> listas = this.listaRepository.listarListas();
        List<Lista> listasQueContienen = new ArrayList<>();
        for (Lista lista : listas) {
            if (lista.existeAnime(idAnime)) listasQueContienen.add(lista);
        }
        return listasQueContienen;
    }

    @Override
    public List<Lista> listarListas() {
        return this.listaRepository.listarListas();
    }
}
