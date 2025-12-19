package service;

import model.Anime;

import java.util.ArrayList;
import java.util.List;

public class RecomendacionTopN implements CriterioRecomendacion{

    @Override
    public List<Anime> recomendar(List<Anime> animes, int n) {
        List<Anime> copia = new ArrayList<>(animes);
        copia.sort((a, b) -> Integer.compare(b.getCalificacionUsuarios(), a.getCalificacionUsuarios()));
        return copia.subList(0, Math.min(n, copia.size())); // retorna una sublista desde la posicion 0 a n
    }
}
