package ru.yandex.practicum.filmorate.servis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;

@Slf4j
@Getter
public class ManagerUsers {
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
            log.info("Данные корректно заполнены");
            return true;
        }
    }

    public User addNewUser(User user) throws ValidationException {
        if (validate(user)) {
            user.setId(nextId++);
            users.put(user.getId(), user);
        }
        return user;
    }

    @PutMapping
    public void makeUpdateUser(User user) throws ValidationException {
        if (validate(user)) {
            if (users.containsKey(user.getId())) {
                users.replace(user.getId(), user);
            } else {
                throw new ValidationException("id don't exist");
            }
        }
    }
}
