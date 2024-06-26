package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.films.FilmStorage;
import ru.yandex.practicum.filmorate.storage.users.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public ArrayList<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    public List<Film> getTopPopularFilms(Integer count) {
        if (count < 0) {
            log.error("Количество фильмов не должно быть отрицательным");
            throw new ValidationException("Количество фильмов не должно быть отрицательным");
        }
        log.info("Инициализированно получение популярных фильмов");
        return getFilms().stream()
                .sorted((f1, f2) -> f2.getUserLikes().size() - f1.getUserLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }
}
