package service;

import model.Anime;
import model.EstadosAnime;

import java.util.List;

public class FiltroPorEstado extends FiltradorTemplate{
    private final EstadosAnime estadoBuscado;

    public FiltroPorEstado(EstadosAnime estadoBuscado) {
        this.estadoBuscado = estadoBuscado;
    }

    @Override
    public List<Anime> filtrar(List<Anime> animes) {
        return super.filtrar(animes);
    }

    @Override
    protected boolean cumpleCondicion(Anime anime) {
        return anime.getEstado() == estadoBuscado;
    }
}
