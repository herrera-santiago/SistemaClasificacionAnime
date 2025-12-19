package ui;

import model.Generos;

public class RecomendarDTO {
    public String tipo;
    public int cantidad;
    public Generos genero;

    public RecomendarDTO(String tipo, int cantidad, Generos genero) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.genero = genero;
    }
}
