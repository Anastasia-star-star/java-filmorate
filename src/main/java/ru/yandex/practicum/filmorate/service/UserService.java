package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Getter
public class UserService {
    private int nextId = 1;
    private final HashMap<Integer, User> users = new HashMap<>();
    private static final LocalDate DATE = LocalDate.now();

    public boolean validate(User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
            log.info("В логине присутствуют пробелы");
            throw new ValidationException("В логине присутствуют пробелы");
        } else if (user.getBirthday().isAfter(DATE)) {
            log.info("День рождения пользователя в будущем");
            throw new ValidationException("День рождения пользователя в будущем");
        } else {
            if (user.getName() == null || user.getName().isBlank()) {
                user.setName(user.getLogin());
                log.info("Имя пользователя пустое, будет использоваться логин");
            }
            log.info("Инициализировано добавление фильма");
            return true;
        }
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public User createUser(User user) throws ValidationException {
        if (validate(user)) {
            user.setId(nextId++);
            users.put(user.getId(), user);
        }
        return user;
    }

    public User updateUser(User user) throws ValidationException {
        if (validate(user)) {
            if (users.containsKey(user.getId())) {
                users.replace(user.getId(), user);
            } else {
                log.info("id don't exist");
                throw new ValidationException("id don't exist");
            }
        }
        return user;
    }
}
