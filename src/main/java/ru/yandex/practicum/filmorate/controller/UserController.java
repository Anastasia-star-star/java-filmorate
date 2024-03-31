package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Slf4j
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Validated
    public User create(@Valid @RequestBody User user) {
        user = ValidatorControllers.validateUser(user);
        User newUser = userService.create(user);
        log.debug("Добавлен новый пользователь");
        return newUser;
    }

    @PutMapping
    @Validated
    public User update(@Valid @RequestBody User user) {
        user = ValidatorControllers.validateUser(user);
        User newUser = userService.update(user);
        log.debug("Обновлен пользователь");
        return newUser;
    }

    @DeleteMapping
    @Validated
    public void delete(@Valid @RequestBody User user) {
        userService.delete(user);
        log.debug("Удалён пользователь: {}", user);
    }

    @GetMapping
    public List<User> findUsers() {
        List<User> users = userService.findUsers();
        log.debug("Получен список пользователей");
        return users;
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable long userId) {
        User user = userService.findUserById(userId);
        log.debug("Получен пользователь");
        return user;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public boolean addInFriends(@PathVariable long id, @PathVariable long friendId) {
        if (userService.addInFriends(id, friendId)) {
            return true;
        }
        return false;
    }

    @DeleteMapping("{id}/friends/{friendId}")
    public boolean deleteFromFriends(@PathVariable long id, @PathVariable long friendId) {
        if (userService.deleteFromFriends(id, friendId)) {
            return true;
        }
        return false;
    }

    @GetMapping("/{id}/friends")
    public List<User> findFriends(@PathVariable long id) {
        List<User> users = userService.findFriends(id);
        log.debug("Получен список пользователей, являющимися друзьями пользователя с id = {}, " +
                "количество = {}", id, users.size());
        return users;
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> findMutualFriends(@PathVariable long id, @PathVariable long otherId) {
        List<User> users = userService.findMutualFriends(id, otherId);
        log.debug("Получен список друзей пользователя");
        return users;
    }
}