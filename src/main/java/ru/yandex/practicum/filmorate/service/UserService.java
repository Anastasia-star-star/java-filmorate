package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.users.UserStorage;

import java.util.ArrayList;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage useStorage) {
        this.userStorage = useStorage;
    }


    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public ArrayList<User> getUsers() {
        return userStorage.getUsers();
    }

    public ArrayList<User> getFriendsById(Integer id) {
        return userStorage.getFriendsById(id);
    }

    public User getUserById(Integer id) {
        return userStorage.getUserById(id);
    }
}
