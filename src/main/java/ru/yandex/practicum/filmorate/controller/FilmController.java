package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/films")
//@Component
public class FilmController {
    private final InMemoryFilmStorage manager = new InMemoryFilmStorage();
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping
    public Collection<Film> getAllFilms() {
        return manager.getAll();
    }

    @GetMapping("/popular")
    public List<Film> getTopPopularFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            log.info("Получен запрос к эндпоинту /films/popular");
            return manager.getTopPopularFilms(10);
        }
        return manager.getTopPopularFilms(count);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        return manager.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        return manager.updateFilm(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public Film putLike(@PathVariable("id") Integer id,
                        @PathVariable("userId") Integer userId) throws ValidationException {
        return manager.putLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Film deleteLike(@PathVariable("id") Integer id,
                           @PathVariable("userId") Integer userId) throws ValidationException {
        return manager.deleteLike(id, userId);
    }
}
