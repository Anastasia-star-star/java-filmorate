package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RestController
@RequestMapping
@Slf4j
@Validated
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @DeleteMapping("/films")
    @Validated
    public void delete(@Valid @RequestBody Film film) {
        filmService.delete(film);
        log.debug("Удалён фильм: {}", film);
    }

    @PostMapping("/films")
    @Validated
    public Film create(@Valid @RequestBody Film film) {
        film = ValidatorControllers.validateFilm(film);
        Film newFilm = filmService.create(film);
        log.debug("Добавлен новый фильм: {}", newFilm);
        return newFilm;
    }

    @PutMapping("/films")
    @Validated
    public Film update(@Valid @RequestBody Film film) {
        ValidatorControllers.validateFilm(film);
        Film newFilm = filmService.update(film);
        log.debug("Обновлен фильм: {}", newFilm);
        return newFilm;
    }

    @GetMapping("/films")
    public List<Film> findFilms() {
        List<Film> films = filmService.findFilms();
        log.debug("Получен список фильмов, количество = {}", films.size());
        return films;
    }

    @GetMapping("/films/{filmId}")
    public Film findFilmById(@PathVariable long filmId) {
        Film film = filmService.findFilmById(filmId);
        log.debug("Получен фильм с id = {}", filmId);
        return film;
    }

    @PutMapping("/films/{id}/like/{userId}")
    public boolean like(@PathVariable long id, @PathVariable long userId) {
        if (filmService.like(id, userId)) {
            log.debug("Пользователь id = {} лайкнул фильм id = {}", userId, id);
            return true;
        }
        return false;
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public boolean dislike(@PathVariable long id, @PathVariable long userId) {
        if (filmService.dislike(id, userId)) {
            log.debug("Пользователь id = {} удалил лайк с фильма id = {}", userId, id);
            return true;
        }
        return false;
    }

    @GetMapping("/films/popular")
    public List<Film> findPopularFilms(@RequestParam(defaultValue = "10") String count) {
        int countInt = Integer.parseInt(count);
        if (countInt < 0) {
            String message = "Параметр count не может быть отрицательным!";
            log.warn(message);
            throw new ValidationException(message);
        }
        List<Film> films = filmService.findPopularFilms(countInt);
        log.debug("Получен список из первых {} фильмов по количеству лайков, " +
                "количество = {}", countInt, films.size());
        return films;
    }

    @GetMapping("/genres")
    public List<Genre> findGenres() {
        List<Genre> genres = filmService.findGenres();
        log.debug("Получен список жанров");
        return genres;
    }

    @GetMapping("/genres/{id}")
    // получение жанра по id
    public Genre findGenreById(@PathVariable long id) {
        Genre genre = filmService.findGenreById(id);
        return genre;
    }

    @GetMapping("/mpa")
    public List<MPA> findRatingMPAs() {
        List<MPA> ratingMPAs = filmService.findRatingMPAs();
        log.debug("Получен список рейтингов");
        return ratingMPAs;
    }

    @GetMapping("/mpa/{id}")
    public MPA findRatingMPAById(@PathVariable long id) {
        MPA ratingMPA = filmService.findRatingMPAById(id);
        log.debug("Получен рейтинг МПА");
        return ratingMPA;
    }

}