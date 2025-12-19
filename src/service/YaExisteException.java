package service;

import model.ValidacionException;

public class YaExisteException extends ValidacionException {
    public YaExisteException(String message) {
        super(message);
    }
}
