package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.servis.ManagerUsers;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    public final ManagerUsers manager = new ManagerUsers();

    @GetMapping
    public Collection<User> getAllUsers() {
        return manager.getUsers().values();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        manager.addNewUser(user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        manager.makeUpdateUser(user);
        return user;
    }
}
