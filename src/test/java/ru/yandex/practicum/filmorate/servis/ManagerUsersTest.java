package ru.yandex.practicum.filmorate.servis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagerUsersTest {

    public final ManagerUsers manager = new ManagerUsers();

    @Test
    void validateOK() throws ValidationException {
        final User userOk = new User(0, "aiarkhipova@yandex.ru", "la-la", "Anastasia",
                LocalDate.now().minusYears(22));
        assertTrue(manager.validate(userOk));
    }
}
