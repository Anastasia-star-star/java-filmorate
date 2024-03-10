/*package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerFilmsTest {
    public final FilmService manager = new FilmService();

    @Test
    void validateOK() throws ValidationException {
        final Film validFilm = new Film(0, "Завтрак у Тифани",
                "Мелодрамма", LocalDate.now().minusYears(10), 58);
        assertTrue(manager.validate(validFilm));

    }

}*/