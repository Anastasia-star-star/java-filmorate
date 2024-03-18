package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.users.InMemoryUserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagerUsersTest {

    public final InMemoryUserStorage manager = new InMemoryUserStorage();

    @Test
    void validateOK() {
        final User userOk = new User(0, "aiarkhipova@yandex.ru", "la-la", "Anastasia",
                LocalDate.now().minusYears(22), null);
        assertTrue(manager.validate(userOk));
    }
}
