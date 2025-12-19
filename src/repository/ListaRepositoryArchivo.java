package repository;

import model.Lista;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaRepositoryArchivo implements IListaRepository{
    private static final String ARCHIVO_LISTAS = "data/listas.dat";

    @Override
    public void guardarLista(Lista nuevaLista) {
        List<Lista> listas = cargarDesdeArchivo();
        int nuevoId = 1;
        if (!listas.isEmpty()) {
            nuevoId = listas.get(listas.size()-1).getId() + 1;
        }
        nuevaLista.setId(nuevoId);
        listas.add(nuevaLista);
        guardarEnArchivo(listas);
    }

    @Override
    public void actualizarLista(Lista listaActualizada) throws NoEncontradoException {
        List<Lista> listas = cargarDesdeArchivo();
        boolean actualizado = false;
        for (int i = 0; i < listas.size(); i++) {
            if (listas.get(i).getId() == listaActualizada.getId()) {
                listas.set(i, listaActualizada);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            throw new NoEncontradoException("Lista no encontrada");
        }
        guardarEnArchivo(listas);
    }

    @Override
    public void eliminarLista(String nombre) {
        List<Lista> listas = cargarDesdeArchivo();
        listas.removeIf(lista -> lista.getNombre().equals(nombre));
        guardarEnArchivo(listas);
    }

    @Override
    public List<Lista> listarListas() {
        return cargarDesdeArchivo();
    }

    @Override
    public List<Lista> obtenerListaPorNombre(String nombre) {
        List<Lista> listas = cargarDesdeArchivo();
        List<Lista> listasFiltrado = new ArrayList<>();
        for (Lista lista : listas){
            if (lista.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                listasFiltrado.add(lista);
            }
        }
        return listasFiltrado;
    }

    @Override
    public Lista obtenerListaPorId(int id) throws NoEncontradoException {
        List<Lista> listas = cargarDesdeArchivo();
        for (Lista lista : listas) {
            if (lista.getId() == id) return lista;
        }
        throw new NoEncontradoException("Lista" + id + ", no encontrada.");
    }

    /* ********* LOGICA ESCRITURA Y LECTURA DE ARCHIVO SERIALIZADO ********* */
    private void guardarEnArchivo(List<Lista> listas) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ARCHIVO_LISTAS))) {

            oos.writeObject(listas);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }

    @SuppressWarnings("unchecked")
        private List<Lista> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_LISTAS);

        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {

            return (List<Lista>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer archivo", e);
        }
    }
}
