package service;

import model.ValidacionException;

public class AnimeYaExisteException extends ValidacionException {
    public AnimeYaExisteException(String message) {
        super(message);
    }
}
