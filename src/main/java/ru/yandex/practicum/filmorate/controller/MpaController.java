package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/mpa")
@Slf4j
@Validated
@RequiredArgsConstructor
public class MpaController {
    private final FilmService filmService;

    @GetMapping
    public List<Mpa> findRatingMPAs() {
        List<Mpa> ratingMpas = filmService.findRatingMPAs();
        log.debug("Получен список рейтингов");
        return ratingMpas;
    }

    @GetMapping("/{id}")
    public Mpa findRatingMPAById(@PathVariable long id) {
        Mpa ratingMpa = filmService.findRatingMPAById(id);
        log.debug("Получен рейтинг МПА");
        return ratingMpa;
    }
}
