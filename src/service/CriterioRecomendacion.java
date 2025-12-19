package service;

import model.Anime;

import java.util.List;

public interface CriterioRecomendacion {
    List<Anime> recomendar(List<Anime> animes, int n);
}
