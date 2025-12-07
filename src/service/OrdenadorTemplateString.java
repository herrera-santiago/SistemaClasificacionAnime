package service;

import model.Anime;
import model.Ordenamientos;

import java.util.Collections;
import java.util.List;

public abstract class OrdenadorTemplateString implements CriterioOrdenamiento {

    // Cada subclase define QUÉ se compara
    protected abstract String obtenerValorString(Anime anime);

    @Override
    public void ordenar(List<Anime> animes) {
        ordenar(animes, Ordenamientos.ASC); // default
    }

    @Override
    public void ordenar(List<Anime> animes, Ordenamientos orden) {
        quicksort(animes, 0, animes.size() - 1, orden);
    }

    private void quicksort(List<Anime> lista, int inicio, int fin, Ordenamientos orden) {
        if (inicio < fin) {
            int pivotIndex = particionar(lista, inicio, fin, orden);
            quicksort(lista, inicio, pivotIndex - 1, orden);
            quicksort(lista, pivotIndex + 1, fin, orden);
        }
    }

    private int particionar(List<Anime> lista, int inicio, int fin, Ordenamientos orden) {
        String pivot = obtenerValorString(lista.get(fin));
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            String actual = obtenerValorString(lista.get(j));

            int comparacion = actual.compareToIgnoreCase(pivot);

            boolean intercambiar =
                    (orden == Ordenamientos.ASC)
                            ? comparacion <= 0
                            : comparacion >= 0;

            if (intercambiar) {
                i++;
                Collections.swap(lista, i, j);
            }
        }

        Collections.swap(lista, i + 1, fin);
        return i + 1;
    }
}
