package model;

import java.util.ArrayList;
import java.util.List;

public class Anime {
    private int id;
    private final String titulo;
    private final int anioLanzamiento;
    private final Estudios estudio;
    private EstadosAnime estado;
    private final int calificacionUsuarios;
    private final List<Generos> generos;
    private final int cantidadCapitulos;

    public Anime(String titulo, int anioLanzamiento, Estudios estudio, EstadosAnime estado, int calificacionUsuarios, List<Generos> generos, int cantidadCapitulos) throws ValidacionException {
        validarTitulo(titulo);
        validarAnio(anioLanzamiento);
        validarCapitulos(cantidadCapitulos);
        validarCalificacion(calificacionUsuarios);
        validarGeneros(generos);
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.estudio = estudio;
        this.estado = estado;
        this.calificacionUsuarios = calificacionUsuarios;
        this.generos = new ArrayList<>(generos);
        this.cantidadCapitulos = cantidadCapitulos;
    }

    /* GETTERS */
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public Estudios getEstudio() {
        return estudio;
    }

    public EstadosAnime getEstado() {
        return estado;
    }

    public int getCalificacionUsuarios() {
        return calificacionUsuarios;
    }

    public List<Generos> getGeneros() {
        return new ArrayList<>(generos);
    }

    public int getCantidadCapitulos(){
        return cantidadCapitulos;
    }


    /* SETTERS */
    public void agregarGenero(Generos genero){
        this.generos.add(genero);
    }

    public void eliminarGenero(Generos genero){
        this.generos.remove(genero);
    }

    protected void setId(int id) {
        this.id = id;
    }

    public void setEstado(EstadosAnime estado) {
        this.estado = estado;
    }


    /* VALIDACIONES */
    private void validarTitulo(String titulo) throws ValidacionException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ValidacionException("El título no puede estar vacío.");
        }
    }
    private void validarAnio(int anioLanzamiento) throws ValidacionException {
        if (anioLanzamiento > 2025 || anioLanzamiento <= 1900) {
            throw new ValidacionException("Año de lanzamiento inválido.");
        }
    }

    private void validarCapitulos(int cantidadCapitulos) throws ValidacionException{
        if (cantidadCapitulos <= 0) {
            throw new ValidacionException("La cantidad de capitulos tiene que ser mayor a 0.");
        }
    }

    private void validarCalificacion(int calificacionUsuarios) throws ValidacionException{
        if (calificacionUsuarios <= 0 || calificacionUsuarios > 5) {
            throw new ValidacionException("El rango de calificación posible es de 1 a 5.");
        }
    }

    private void validarGeneros(List<Generos> generos) throws  ValidacionException {
        if (generos == null || generos.isEmpty()) {
            throw new ValidacionException("El anime debe tener al menos un género.");
        }
    }
}
