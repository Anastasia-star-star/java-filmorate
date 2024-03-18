package ru.yandex.practicum.filmorate.storage.films;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public interface FilmStorage {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    ArrayList<Film> getFilms();

    HashMap<Integer, Film> getHashMapFilms();
}
