package service;

import model.Anime;
import model.EstadosAnime;
import model.ValidacionException;

import java.util.List;

public class FiltroPorCalificacionMinima extends FiltradorTemplate{
    private final int calificacionMinima;

    public FiltroPorCalificacionMinima(int calificacionMinima) throws ValidacionException {
        if (calificacionMinima < 0 || calificacionMinima > 5) {
            throw new ValidacionException("El rango de calificación posible es de 1 a 5.");
        } else {
            this.calificacionMinima = calificacionMinima;
        }
    }

    @Override
    public List<Anime> filtrar(List<Anime> animes) {
        return super.filtrar(animes);
    }

    @Override
    protected boolean cumpleCondicion(Anime anime) {
        return anime.getCalificacionUsuarios() == calificacionMinima;
    }
}
