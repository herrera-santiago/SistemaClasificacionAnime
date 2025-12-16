package service;

import model.Anime;
import model.Ordenamientos;

import java.util.List;

public interface CriterioOrdenamiento {
    public List<Anime> ordenar(List<Anime> animes);
    public List<Anime> ordenar(List<Anime> animes, Ordenamientos orden);
}

