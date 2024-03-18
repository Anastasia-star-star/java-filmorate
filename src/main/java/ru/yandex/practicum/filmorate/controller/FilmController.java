package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private static final int COUNT_OF_FILMS_DEFAULT = 10;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ArrayList<Film> getAllFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/popular")
    public List<Film> getTopPopularFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            log.info("Получен запрос к эндпоинту /films/popular");
            return filmService.getTopPopularFilms(COUNT_OF_FILMS_DEFAULT);
        }
        return filmService.getTopPopularFilms(count);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmService.updateFilm(film);
    }
}
