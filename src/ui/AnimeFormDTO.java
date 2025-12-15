package ui;

import model.EstadosAnime;
import model.Generos;
import java.util.List;

public class AnimeFormDTO {

    public String titulo;
    public int anio;
    public int capitulos;
    public EstadosAnime estado;
    public int calificacion;
    public List<Generos> generos;

    public AnimeFormDTO(String titulo, int anio, int capitulos,
                        EstadosAnime estado, int calificacion,
                        List<Generos> generos) {
        this.titulo = titulo;
        this.anio = anio;
        this.capitulos = capitulos;
        this.estado = estado;
        this.calificacion = calificacion;
        this.generos = generos;
    }
}
