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
    public List<Anime> ordenar(List<Anime> animes) {
        return super.ordenar(animes);
    }

    @Override
    public List<Anime> ordenar(List<Anime> animes, Ordenamientos orden) {
        return super.ordenar(animes, orden);
    }
}
