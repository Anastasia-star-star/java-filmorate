package ru.yandex.practicum.filmorate.servis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerFilmsTest {
    public final ManagerFilms manager = new ManagerFilms();

    @Test
    void validateOK() throws ValidationException {
        final Film validFilm = new Film(0, "Завтрак у Тифани",
                "Мелодрамма", LocalDate.now().minusYears(10), 58);
        assertTrue(manager.validate(validFilm));

    }

}