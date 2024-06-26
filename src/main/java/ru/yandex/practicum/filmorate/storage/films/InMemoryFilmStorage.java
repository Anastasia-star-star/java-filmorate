package ru.yandex.practicum.filmorate.storage.films;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ObjectDoNotExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.*;

@Getter
@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final HashMap<Integer, Film> films = new HashMap<>();
    private int nextId = 1;
    private static final LocalDate DATE = LocalDate.of(1895, 12, 28);

    public boolean validate(Film film) {
        if (film == null) {
            log.error("Передан пустой объект");
            throw new ValidationException("Передан пустой объект");
        }
        if (film.getReleaseDate().isBefore(DATE)) {
            log.error("Дата реализации фильма раньше допустимой даты");
            throw new ValidationException("Дата реализации фильма раньше допустимой даты");
        } else {
            return true;
        }
    }

    @Override
    public HashMap<Integer, Film> getHashMapFilms() {
        return films;
    }

    @Override
    public ArrayList<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film createFilm(Film film) {
        if (validate(film)) {
            film.setId(nextId++);
            films.put(film.getId(), film);
        }
        log.info("Инициализировано добавление фильма");
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (validate(film)) {
            if (films.containsKey(film.getId())) {
                films.replace(film.getId(), film);
                log.info("Инициализировано обновление фильма");
            } else {
                log.error("Передан несуществующий объект");
                throw new ObjectDoNotExistException("Передан несуществующий объект при обновлении фильма");
            }
        }
        return film;
    }
}
