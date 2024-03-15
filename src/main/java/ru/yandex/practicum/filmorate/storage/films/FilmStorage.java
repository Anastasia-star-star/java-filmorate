package ru.yandex.practicum.filmorate.storage.films;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;

@Component
public interface FilmStorage {
    Film createFilm(Film film) throws ValidationException;

    Film updateFilm(Film film) throws ValidationException;

    ArrayList<Film> getFilms();


}
