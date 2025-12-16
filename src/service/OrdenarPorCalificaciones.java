package service;

import model.Anime;
import model.Ordenamientos;
import java.util.List;

public class OrdenarPorCalificaciones extends OrdenadorTemplate{

    @Override
    protected int obtenerValor(Anime anime) {
        return anime.getCalificacionUsuarios();
    }

    @Override
    public List<Anime> ordenar(List<Anime> animes, Ordenamientos orden) {
        return super.ordenar(animes, orden);
    }

    @Override
    public List<Anime> ordenar(List<Anime> animes) {
        return super.ordenar(animes);
    }
}
