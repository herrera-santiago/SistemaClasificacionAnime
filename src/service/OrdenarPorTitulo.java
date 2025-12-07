package service;

import model.Anime;
import model.Ordenamientos;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class OrdenarPorTitulo  extends OrdenadorTemplateString{

    @Override
    protected String obtenerValorString(Anime anime) {
        return anime.getTitulo();
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
