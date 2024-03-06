package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.ServiceFilms;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    private final ServiceFilms manager = new ServiceFilms();

    @GetMapping
    public Collection<Film> getAllFilms() {
        return manager.getFilms().values();
    }

    @PostMapping
    public void createFilm(@Valid @RequestBody Film film) throws ValidationException {
        manager.addNewFilm(film);
    }

    @PutMapping
    public void updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        manager.makeUpdateFilm(film);
    }
}
