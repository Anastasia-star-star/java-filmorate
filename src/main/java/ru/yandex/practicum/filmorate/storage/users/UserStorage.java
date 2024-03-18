package ru.yandex.practicum.filmorate.storage.users;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public interface UserStorage {
    HashMap<Integer, User> getHashMapUsers();

    User createUser(User user);

    User updateUser(User user);

    ArrayList<User> getUsers();

    User getUserById(Integer id);

    ArrayList<User> getFriendsById(Integer id);
}
