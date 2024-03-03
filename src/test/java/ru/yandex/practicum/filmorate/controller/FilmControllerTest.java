package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    FilmController filmController = new FilmController();

    @Test
    void validateOK() throws ValidationException {
        final Film validFilm = new Film(0, "Завтрак у Тифани", "Мелодрамма", LocalDateTime.now().minusYears(10), 58);
        assertTrue(filmController.validate(validFilm));
    }

    @Test
    void validateFail() throws ValidationException {
        final Film filmFail = new Film(1, " ", "Мелодрамма", LocalDateTime.now().minusYears(10), 58);
        Exception exception = assertThrows(ValidationException.class, () -> filmController.validate(filmFail));
        assertEquals(exception, "Поле с названием фильма пустое");

        final Film filmFail2 = new Film(2, "Завтрак у Тифани", "Мелодраммаsdfghjkljhgfdfghjkljhgfdsfghjjhgfdfvgbnhmjjmhngbfjhgfh" + "fdghjkl;';lkjhgfdfghjklkjhgfdghjklkjhgfdghjkjhgfdgbhnmjhgfgbnhgnh" + "dfgbnhmjk,l./;tfghjkljhgfdsfghjkjhgfdsfghjkhgfdsfghjkjhgfdfghjkgf" + "fdghjkl;';lkjhgfdfghjklkjhgfdghjklkjhgfdghjkjhgfdgbhnmjhgfgbnhgnh" + "dfgbnhmjk,l./;tfghjkljhgfdsfghjkjhgfdsfghjkhgfdsfghjkjhgfdfghjkgf" + "fdghjkl;';lkjhgfdfghjklkjhgfdghjklkjhgfdghjkjhgfdgbhnmjhgfgbnhgnh" + "dfgbnhmjk,l./;tfghjkljhgfdsfghjkjhgfdsfghjkhgfdsfghjkjhgfdfghjkgf" + "fdghjklkjhgvdfghjkjhgfdsdfghjkjhgfdsasdfghjklkjhgfdsd" + "fghjkjhgfdasdfg", LocalDateTime.now().minusYears(10), 58);
        Exception exception2 = assertThrows(ValidationException.class, () -> filmController.validate(filmFail2));
        assertEquals(exception2, "Поле с описанием фильма превышает 200 символов");

        final Film filmFail3 = new Film(3, "Breakfast by Tifany ", "Мелодрамма", LocalDateTime.now().minusYears(200), 58);
        Exception exception3 = assertThrows(ValidationException.class, () -> filmController.validate(filmFail3));
        assertEquals(exception3, "Дата реализации фильма раньше допустимой даты");

        final Film filmFail4 = new Film(4, "Breakfast by Tifany ", "Мелодрамма", LocalDateTime.now().minusYears(10), -2);
        Exception exception4 = assertThrows(ValidationException.class, () -> filmController.validate(filmFail4));
        assertEquals(exception4, "Длительность фильма отрицательна");

    }
}