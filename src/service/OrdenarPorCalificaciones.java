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
    public void ordenar(List<Anime> animes, Ordenamientos orden) {
        super.ordenar(animes, orden);
    }

    @Override
    public void ordenar(List<Anime> animes) {
        super.ordenar(animes);
    }
}
