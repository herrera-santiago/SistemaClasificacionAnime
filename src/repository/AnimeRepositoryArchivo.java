package repository;

import model.Anime;
import model.Generos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnimeRepositoryArchivo implements IAnimeRepository {
    private static final String ARCHIVO_ANIMES = "data/animes.dat";

    @Override
    public void guardarAnime(Anime nuevoAnime) {
        List<Anime> animes = cargarDesdeArchivo();
        int nuevoId = 1;
        if (!animes.isEmpty()) {
            nuevoId = animes.get(animes.size() - 1).getId() + 1; // el nuevo id es +1 del ultimo de la lista.
        }
        nuevoAnime.setId(nuevoId);
        animes.add(nuevoAnime);
        guardarEnArchivo(animes);
    }

    @Override
    public List<Anime> obtenerAnimesPorTitulo(String titulo) {
        List<Anime> animes = cargarDesdeArchivo();
        List<Anime> animesFiltrado = new ArrayList<>();
        for (Anime anime: animes){
            if (anime.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                animesFiltrado.add(anime);
            }
        }
        return animesFiltrado;
    }

    @Override
    public List<Anime> obtenerAnimesPorRangoAnios(int anioDesde, int anioHasta) {
        List<Anime> animes = cargarDesdeArchivo();
        List<Anime> animesFiltrado = new ArrayList<>();
        for (Anime anime: animes){
            if (anime.getAnioLanzamiento() >= anioDesde && anime.getAnioLanzamiento() <= anioHasta){
                animesFiltrado.add(anime);
            }
        }
        return animesFiltrado;
    }

    @Override
    public List<Anime> obtenerAnimesPorGenero(Generos genero) {
        List<Anime> animes = cargarDesdeArchivo();
        List<Anime> animesFiltrado = new ArrayList<>();
        for (Anime anime: animes){
            if (anime.getGeneros().contains(genero)){
                animesFiltrado.add(anime);
            }
        }
        return animesFiltrado;
    }

    @Override
    public Anime obtenerAnimePorId(int id) throws NoEncontradoException {
        List<Anime> animes = cargarDesdeArchivo();
        for (Anime anime: animes){
            if (anime.getId() == id){
                return anime;
            }
        }
        throw new NoEncontradoException("Anime no encontrado.");
    }

    @Override
    public void eliminarAnime(String titulo) {
        List<Anime> animes = cargarDesdeArchivo();
        animes.removeIf(anime -> anime.getTitulo().equals(titulo));
        guardarEnArchivo(animes);
    }

    @Override
    public void actualizarAnime(Anime animeActualizado) throws NoEncontradoException {
        List<Anime> animes = cargarDesdeArchivo();
        boolean actualizado = false;
        for (int i = 0; i < animes.size(); i++) {
            if (animes.get(i).getId() == animeActualizado.getId()) {
                animes.set(i, animeActualizado);
                actualizado = true;
                break;
            }
        }

        if (!actualizado) {
            throw new NoEncontradoException("Anime no encontrado");
        }
        guardarEnArchivo(animes);
    }

    @Override
    public List<Anime> listarAnimes() {
        return cargarDesdeArchivo();
    }

    /* ********* LOGICA ESCRITURA Y LECTURA DE ARCHIVO SERIALIZADO ********* */
    private void guardarEnArchivo(List<Anime> animes) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ARCHIVO_ANIMES))) {

            oos.writeObject(animes);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Anime> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_ANIMES);

        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {

            return (List<Anime>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer archivo", e);
        }
    }
}
