package ru.yandex.practicum.filmorate.servis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Slf4j
public class ManagerFilms {
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

    public void addNewFilm(Film film) throws ValidationException {
        if (validate(film)) {
            log.info("Данные о фильме успешно добавлены");
            film.setId(nextId++);
            films.put(film.getId(), film);
            log.info("Данные о фильме успешно добавлены");
        }
    }

    public void makeUpdateFilm(Film film) throws ValidationException {
        if (validate(film)) {
            if (films.containsKey(film.getId())) {
                films.replace(film.getId(), film);
                log.info("Данные о фильме успешно добавлены");
            } else {
                throw new ValidationException("id не найдено");
            }
        }
    }

}
