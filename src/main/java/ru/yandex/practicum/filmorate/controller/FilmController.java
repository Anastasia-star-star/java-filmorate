package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    HashMap<Integer, Film> films = new HashMap<>();
    public final static LocalDateTime DATE = LocalDateTime.of(1985, 12, 28, 0, 0);
    public final static int LONG_OF_DESCRIPTION = 200;

    public boolean validate(Film film) throws ValidationException{
        if (film.getName().isBlank()){
            throw new ValidationException("Поле с названием фильма пустое");
        }
        else if (film.getDescription().length() > LONG_OF_DESCRIPTION){
            throw new ValidationException("Поле с описанием фильма превышает 200 символов");
        }
        else if (film.getReleaseDate().isBefore(DATE)){
            throw new ValidationException("Дата реализации фильма раньше допустимой даты");
        }
        else if (film.getDuration() < 0){
            throw new ValidationException("Длительность фильма отрицательна");
        }
        else{
            return true;
        }
    }
    @GetMapping
    public Collection<Film> getAllFilms(){
        return films.values();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) throws ValidationException{
        if (validate(film)){
            log.info("Данные о фильме успешно добавлены");
            films.put(film.getId(), film);
        }
        else{
            log.info("Данные о фильме не были добавлены, так как не корректны");
        }
        return film;
    }

    @PutMapping
    public Film UpdateFilm(@RequestBody Film film) throws ValidationException {
        if (validate(film)){
            log.info("Данные о фильме успешно добавлены");
            films.replace(film.getId(), film);
        }
        else{
            log.info("Данные о фильме не были добавлены, так как не корректны");
        }
        return film;
    }
}
