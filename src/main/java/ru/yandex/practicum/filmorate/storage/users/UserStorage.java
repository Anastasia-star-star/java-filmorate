package ru.yandex.practicum.filmorate.storage.users;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;

@Component
public interface UserStorage {
    User createUser(User user) throws ValidationException;

    User updateUser(User user) throws ValidationException;

    ArrayList<User> getUsers();

    User getUserById(Integer id);

    ArrayList<User> getFriendsById(Integer id);
}
