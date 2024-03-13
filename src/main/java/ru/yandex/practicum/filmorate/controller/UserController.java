package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/users")
@Component
public class UserController {
    private final InMemoryUserStorage manager = new InMemoryUserStorage();
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return manager.getUserById(id);
    }

    @GetMapping("/{id}/friends")
    public ArrayList<User> getFriendsById(@PathVariable("id") Integer id) throws ValidationException {
        return manager.getFriendsById(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public ArrayList<User> getCommonFriends(@PathVariable("id") Integer id,
                                            @PathVariable("otherId") Integer otherId) {
        return userService.getCommonFriends(id, otherId);
    }

    @GetMapping
    public ArrayList<User> getAllUsers() {
        return manager.getUsers();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        return manager.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        return manager.updateUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addInFriends(@PathVariable("id") Integer id,
                             @PathVariable("friendId") Integer friendId) throws ValidationException {
        return userService.addInFriends(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User deleteFromFriends(@PathVariable("id") Integer id,
                                  @PathVariable("friendId") Integer friendId) throws ValidationException {
        return userService.deleteFromFriend(id, friendId);
    }
}
