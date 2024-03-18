package ru.yandex.practicum.filmorate.storage.users;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ObjectDoNotExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Getter
@Component
public class InMemoryUserStorage implements UserStorage {
    private int nextId = 1;
    private final HashMap<Integer, User> users = new HashMap<>();
    private static final LocalDate DATE = LocalDate.now();

    @Override
    public HashMap<Integer, User> getHashMapUsers() {
        return users;
    }

    @Override
    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean validate(User user) {
        if (user == null) {
            throw new ValidationException("");
        }
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

    @Override
    public ArrayList<User> getFriendsById(Integer id) {
        if (users.get(id).getFriends() == null) {
            throw new ValidationException("");
        }
        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : users.get(id).getFriends()) {
            listUsers.add(users.get(ind));
        }
        return listUsers;
    }

    @Override
    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public User createUser(User user) {
        if (validate(user)) {
            user.setId(nextId++);
            users.put(user.getId(), user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (validate(user)) {
            if (users.containsKey(user.getId())) {
                users.replace(user.getId(), user);
            } else {
                log.info("id don't exist");
                throw new ObjectDoNotExistException("id don't exist");
            }
        }
        return user;
    }
}
