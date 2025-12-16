package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lista implements Serializable {
     private int id;
    private String nombre;
    private List<Integer> animesIds = new ArrayList<>();

    public Lista(int id, String nombre, List<Integer> animesIds) {
        this.id = id;
        this.nombre = nombre;
        this.animesIds = animesIds;
    }

    public boolean existeAnime(int idAnime) {
        return this.animesIds.contains(idAnime);
    }

    public void agregarAnime(int idAnime) {
        this.animesIds.add(idAnime);
    }
}
