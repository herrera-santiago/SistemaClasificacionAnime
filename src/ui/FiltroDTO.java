package ui;

import model.EstadosAnime;
import model.Generos;

public class FiltroDTO {
    public Generos genero;
    public EstadosAnime estado;
    public int calificacionMinima;

    public FiltroDTO(Generos genero, EstadosAnime estado, int calificacionMinima) {
        this.genero = genero;
        this.estado = estado;
        this.calificacionMinima = calificacionMinima;
    }
}
