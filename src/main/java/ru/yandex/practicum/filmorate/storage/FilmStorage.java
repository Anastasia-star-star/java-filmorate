package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

public interface FilmStorage {
    Film createFilm(Film film) throws ValidationException;

    Film deleteFilm(Film film) throws ValidationException;

    Film updateFilm(Film film) throws ValidationException;
}
