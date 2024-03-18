package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.films.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ManagerFilmsTest {
    public final InMemoryFilmStorage manager = new InMemoryFilmStorage();

    @Test
    void validateOK() {
        final Film validFilm = new Film(0, "Завтрак у Тифани",
                "Мелодрамма", LocalDate.now().minusYears(10), 58, new HashSet<>());
        assertTrue(manager.validate(validFilm));
    }

}