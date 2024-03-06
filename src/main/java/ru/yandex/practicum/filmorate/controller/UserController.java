package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.ServiceUsers;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final ServiceUsers manager = new ServiceUsers();

    @GetMapping
    public Collection<User> getAllUsers() {
        return manager.getUsers().values();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        return manager.addNewUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        return manager.makeUpdateUser(user);
    }
}
