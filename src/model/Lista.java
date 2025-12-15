package model;

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private int id;
    private String nombre;
    private List<Integer> animesId;

    public Lista(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.animesId = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean existeAnime(int idAnime) {
        return animesId.contains(idAnime);
    }

    public void agregarAnime(int idAnime) {
        if (!animesId.contains(idAnime)) {
            animesId.add(idAnime);
        }
    }

    public void eliminarAnime(int idAnime) {
        animesId.remove(Integer.valueOf(idAnime));
    }
}
