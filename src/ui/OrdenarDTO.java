package ui;

import model.Ordenamientos;

public class OrdenarDTO {
    public String campo;
    public Ordenamientos orden;

    public OrdenarDTO(String campo, Ordenamientos orden) {
        this.campo = campo;
        this.orden = orden;
    }
}
