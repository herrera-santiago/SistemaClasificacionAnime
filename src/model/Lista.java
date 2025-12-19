package model;

import service.YaExisteException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lista implements Serializable {
     private int id;
    private String nombre;
    private List<Integer> animesIds = new ArrayList<>();

    public Lista(String nombre, List<Integer> animesIds) {
        this.nombre = nombre;
        this.animesIds = animesIds;
    }

    /* GETTERS */
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Integer> getAnimesIds() {
        return animesIds;
    }

    /* SETTERS */

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnimesIds(List<Integer> animesIds) {
        this.animesIds = animesIds;
    }

    public boolean existeAnime(int idAnime) {
        return this.animesIds.contains(idAnime);
    }

    public void agregarAnime(int idAnime) throws YaExisteException {
        if (existeAnime(idAnime)) {
            throw new YaExisteException("Anime ya existe en la lista");
        }
        this.animesIds.add(idAnime);
    }

}
