package ru.yandex.practicum.filmorate.exception;

public class ObjectDoNotExistException extends RuntimeException {
    public ObjectDoNotExistException(String message) {
        super(message);
    }
}
