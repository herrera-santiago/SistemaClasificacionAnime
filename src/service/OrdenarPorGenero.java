package service;

import model.Anime;
import model.Ordenamientos;

import java.util.List;

public class OrdenarPorGenero extends OrdenadorTemplateString{

    @Override
    protected String obtenerValorString(Anime anime) {
        return anime.getGeneros().toString();
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
