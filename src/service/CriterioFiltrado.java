package service;

import model.Anime;

import java.util.List;

public interface CriterioFiltrado {
    public List<Anime> filtrar(List<Anime> animes);
}
