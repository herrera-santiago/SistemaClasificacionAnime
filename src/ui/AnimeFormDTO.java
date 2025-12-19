package ui;

import model.Estudios;
import model.EstadosAnime;
import model.Generos;

import java.util.List;

public class AnimeFormDTO {
    public String titulo;
    public int anio;
    public Estudios estudio;
    public int capitulos;
    public EstadosAnime estado;
    public int calificacion;
    public List<Generos> generos;

    public AnimeFormDTO() {
        // vacío, para armarlo desde el dialog
    }

    public AnimeFormDTO(String titulo, int anio, Estudios estudio, int capitulos,
                        EstadosAnime estado, int calificacion, List<Generos> generos) {
        this.titulo = titulo;
        this.anio = anio;
        this.estudio = estudio;
        this.capitulos = capitulos;
        this.estado = estado;
        this.calificacion = calificacion;
        this.generos = generos;
    }
}
