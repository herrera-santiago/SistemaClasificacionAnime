package service;

import model.Anime;

import java.util.ArrayList;
import java.util.List;

public abstract class FiltradorTemplate implements CriterioFiltrado{
    @Override
    public List<Anime> filtrar(List<Anime> animes){
        List<Anime> filteredAnimes = new ArrayList<>();
        for (Anime a : animes){
            if (cumpleCondicion(a)){
                filteredAnimes.add(a);
            }
        }
        return filteredAnimes;
    }

    protected abstract boolean cumpleCondicion(Anime anime);
}
