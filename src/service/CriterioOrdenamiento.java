package service;

import model.Anime;
import model.Ordenamientos;

import java.util.List;

public interface CriterioOrdenamiento {
    public void ordenar(List<Anime> animes);
    public void ordenar(List<Anime> animes, Ordenamientos orden);
}

