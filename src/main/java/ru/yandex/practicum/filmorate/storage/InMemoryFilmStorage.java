package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final HashMap<Integer, Film> films = new HashMap<>();
    private int nextId = 1;
    private static final LocalDate DATE = LocalDate.of(1895, 12, 28);

    public boolean validate(Film film) throws ValidationException {
        if (film.getReleaseDate().isBefore(DATE)) {
            log.info("Дата реализации фильма раньше допустимой даты");
            throw new ValidationException("Дата реализации фильма раньше допустимой даты");
        } else {
            return true;
        }
    }

    public Collection<Film> getAll() {
        return films.values();
    }

    @Override
    public Film createFilm(Film film) throws ValidationException {
        if (validate(film)) {
            film.setId(nextId++);
            films.put(film.getId(), film);
            log.info("Инициализировано добавление фильма");
        }
        return film;
    }

    @Override
    public Film updateFilm(Film film) throws ValidationException {
        if (validate(film)) {
            if (films.containsKey(film.getId())) {
                films.replace(film.getId(), film);
                log.info("Инициализировано добавление фильма");
            } else {
                log.info("id не найдено");
                throw new ValidationException("id не найдено");
            }
        }
        return film;
    }

    @Override
    public Film deleteFilm(Film film) throws ValidationException {
        //
        return film;
    }

    public Film putLike(Integer id, Integer userId) throws ValidationException {
        Set<Integer> userLikes = new HashSet<>();
        if (films.get(id).getUserLikes() != null) {
            userLikes = films.get(id).getUserLikes();
        }
        userLikes.add(userId);
        films.get(id).setUserLikes(userLikes);
        return films.get(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Film deleteLike(Integer id, Integer userId) throws ValidationException {
        Set<Integer> userLikes = new HashSet<>();
        if (films.get(id).getUserLikes() == null) {
            log.info("Лайков для удаления нет");
            throw new ValidationException("");
        }
        userLikes = films.get(id).getUserLikes();
        userLikes.remove(userId);
        films.get(id).setUserLikes(userLikes);
        return films.get(id);
    }

    public List<Film> getTopPopularFilms(Integer count) {
        if (count < 0) {
            log.error("Количество фильмов не должно быть отрицательным");
        }
        return films.values().stream()
                .sorted((f1, f2) -> f2.getUserLikes().size() - f1.getUserLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

}
