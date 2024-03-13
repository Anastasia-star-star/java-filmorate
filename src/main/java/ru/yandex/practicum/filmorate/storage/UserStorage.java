package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;

public interface UserStorage {
    User createUser(User user) throws ValidationException;

    User deleteUser(User user) throws ValidationException;

    User updateUser(User user) throws ValidationException;
    ArrayList<User> getUsers();
}
