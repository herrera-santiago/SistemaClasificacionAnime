package service;

import model.Anime;
import model.Ordenamientos;

import java.util.List;

public class OrdenarPorAnio extends OrdenadorTemplate{
    @Override
    protected int obtenerValor(Anime anime) {
        return anime.getAnioLanzamiento();
    }

    @Override
    public void ordenar(List<Anime> animes) {
        super.ordenar(animes);
    }

    @Override
    public void ordenar(List<Anime> animes, Ordenamientos orden) {
        super.ordenar(animes, orden);
    }
}
