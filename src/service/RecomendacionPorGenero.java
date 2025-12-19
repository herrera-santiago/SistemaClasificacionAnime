package service;

import model.Anime;
import model.Generos;

import java.util.ArrayList;
import java.util.List;

public class RecomendacionPorGenero implements CriterioRecomendacion {

    private Generos genero;

    public RecomendacionPorGenero(Generos genero) {
        this.genero = genero;
    }

    @Override
    public List<Anime> recomendar(List<Anime> animes, int n) {
        List<Anime> filtrados = new ArrayList<>();

        for (Anime anime : animes) {
            if (anime.getGeneros().contains(genero)) {
                filtrados.add(anime);
            }
        } // filtramos los animes por el genero adecuado
        filtrados.sort((a, b) -> Integer.compare(b.getCalificacionUsuarios(), a.getCalificacionUsuarios())); // ordenamos
        return filtrados.subList(0, Math.min(n, filtrados.size())); // nos quedamos con la sublista de los filtrados.
    }
}
