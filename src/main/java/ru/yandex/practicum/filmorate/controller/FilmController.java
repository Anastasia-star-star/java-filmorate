package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    int nextId = 1;
    public static final LocalDate DATE = LocalDate.of(1895, 12, 28);

    public boolean validate(Film film) throws ValidationException {
        if (film.getReleaseDate().isBefore(DATE)) {
            throw new ValidationException("Дата реализации фильма раньше допустимой даты");}
        else {
            return true;
        }
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (validate(film)) {
            log.info("Данные о фильме успешно добавлены");
            film.setId(nextId++);
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
            if (films.containsKey(film.getId())){
                films.replace(film.getId(), film);
            }
        } else {
            log.info("Данные о фильме не были добавлены, так как не корректны");

        }
        return film;
    }
}
