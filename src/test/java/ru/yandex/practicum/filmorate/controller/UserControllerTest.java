package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserController userController = new UserController();

    @Test
    void validateOK() throws ValidationException {
        final User userOk = new User(0, "aiarkhipova@yandex.ru", "la-la", "Anastasia",
                LocalDateTime.now().minusYears(22));
        assertTrue(userController.validate(userOk));
    }

    void validateFail() {
        final User userFail = new User(1, "aiarkhipova@yandex.ru", "", "Anastasia",
                LocalDateTime.now().minusYears(22));
        Exception exception = assertThrows(ValidationException.class, () -> userController.validate(userFail));
        assertEquals(exception.getMessage(), "Пустое поле с логином");

        final User userFail2 = new User(2, "aiarkhipova@yandex.ru", "la la", "Anastasia",
                LocalDateTime.now().minusYears(22));
        Exception exception2 = assertThrows(ValidationException.class, () -> userController.validate(userFail2));
        assertEquals(exception2.getMessage(), "В логине присутствуют пробелы");

        final User userFail3 = new User(3, "aiarkhipovayandex.ru", "", "Anastasia",
                LocalDateTime.now().minusYears(22));
        Exception exception3 = assertThrows(ValidationException.class, () -> userController.validate(userFail3));
        assertEquals(exception3.getMessage(), "В электронной почте нет символа собачки");

        final User userFail4 = new User(4, "", "", "Anastasia",
                LocalDateTime.now().minusYears(22));
        Exception exception4 = assertThrows(ValidationException.class, () -> userController.validate(userFail4));
        assertEquals(exception4.getMessage(), "Пустое поле с электронной почтой");

        final User userFail5 = new User(5, "aiarkhipova@yandex.ru", "", "Anastasia",
                LocalDateTime.now().plusYears(50));
        Exception exception5 = assertThrows(ValidationException.class, () -> userController.validate(userFail5));
        assertEquals(exception5.getMessage(), "День рождения пользователя в будущем");
    }
}