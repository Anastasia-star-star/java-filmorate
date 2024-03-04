package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    HashMap<Integer, Film> films = new HashMap<>();
    public static final LocalDate DATE = LocalDate.of(1985, 12, 28);
    public static final int LONG_OF_DESCRIPTION = 200;

    public boolean validate(Film film) throws ValidationException {
        if (film.getName().isBlank()) {
            throw new ValidationException("Поле с названием фильма пустое");
        } else if (film.getDescription().length() > LONG_OF_DESCRIPTION) {
            throw new ValidationException("Поле с описанием фильма превышает 200 символов");
        } else if (film.getReleaseDate().isBefore(DATE)) {
            throw new ValidationException("Дата реализации фильма раньше допустимой даты");
        } else if (film.getDuration() < 0) {
            throw new ValidationException("Длительность фильма отрицательна");
        } else if (films.containsKey(film.getId())) {
            throw new ValidationException("id фильма уже существует");
        }
        else {
            return true;
        }
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (validate(film)) {
            log.info("Данные о фильме успешно добавлены");
            films.put(film.getId(), film);
        } else {
            log.info("Данные о фильме не были добавлены, так как не корректны");
        }
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (validate(film)) {
            log.info("Данные о фильме успешно добавлены");
            films.replace(film.getId(), film);
        } else {
            log.info("Данные о фильме не были добавлены, так как не корректны");
        }
        return film;
    }
}
