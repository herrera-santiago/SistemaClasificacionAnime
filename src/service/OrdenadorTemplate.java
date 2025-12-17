package service;

import model.Anime;
import model.Ordenamientos;

import java.util.Collections;
import java.util.List;

public abstract class OrdenadorTemplate implements CriterioOrdenamiento {

    // Cada subclase define QUÉ se compara
    protected abstract int obtenerValor(Anime anime);

    @Override
    public List<Anime> ordenar(List<Anime> animes) {
        return ordenar(animes, Ordenamientos.DESC); // default
    }

    @Override
    public List<Anime> ordenar(List<Anime> animes, Ordenamientos orden) {
        return quicksort(animes, 0, animes.size() - 1, orden);
    }

    private List<Anime> quicksort(List<Anime> lista, int inicio, int fin, Ordenamientos orden) {
        if (inicio < fin) {
            int pivotIndex;
            if (orden == Ordenamientos.ASC) {
                pivotIndex = particionarAsc(lista, inicio, fin);
            } else {
                pivotIndex = particionarDesc(lista, inicio, fin);
            }
            quicksort(lista, inicio, pivotIndex - 1, orden);
            quicksort(lista, pivotIndex + 1, fin, orden);
        }
        return lista;
    }

    private int particionarAsc(List<Anime> lista, int inicio, int fin) {
        int pivot = obtenerValor(lista.get(fin));
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (obtenerValor(lista.get(j)) <= pivot) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, fin);
        return i + 1;
    }

    private int particionarDesc(List<Anime> lista, int inicio, int fin) {
        int pivot = obtenerValor(lista.get(fin));
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (obtenerValor(lista.get(j)) >= pivot) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, fin);
        return i + 1;
    }
}
