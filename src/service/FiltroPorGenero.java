package service;

import model.Anime;
import model.Generos;

import java.util.ArrayList;
import java.util.List;

public class FiltroPorGenero implements CriterioFiltrado{
    private Generos generoBuscado;

    public FiltroPorGenero(Generos generoBuscado) {
        this.generoBuscado = generoBuscado;
    }

    @Override
    public List<Anime> filtrar(List<Anime> animes) {
        List<Anime> resultado = new ArrayList<>();
        for (Anime a : animes) {
            if (a.getGeneros().contains(generoBuscado)) {
                resultado.add(a);
            }
        }
        return resultado;
    }
}
