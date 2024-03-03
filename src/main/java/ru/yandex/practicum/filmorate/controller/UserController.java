package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    HashMap<Integer, User> users = new HashMap<>();
    public final static LocalDateTime DATE = LocalDateTime.now();

    public boolean validate(User user) throws ValidationException{
        if (user.getLogin().isBlank()){
            throw new ValidationException("Пустое поле с логином");
        }
        else if (user.getLogin().contains(" ")){
            throw new ValidationException("В логине присутствуют пробелы");
        }
        else if (user.getEmail().isBlank()){
            throw new ValidationException("Пустое поле с электронной почтой");
        }
        else if (!user.getEmail().contains("@")){
            throw new ValidationException("В электронной почте нет символа собачки");
        }
        else if (user.getBirthday().isAfter(DATE)){
            throw new ValidationException("День рождения пользователя в будущем");
        }
        else{
            if (user.getName().isBlank()){
                user.setName(user.getLogin());
                log.info("Имя пользователя пустое, будет использоваться логин");
            }
            else{
                log.info("Данные корректно заполнены");
            }
            return true;
        }
    }

    @GetMapping
    public Collection<User> getAllUsers(){
        return users.values();
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws ValidationException {
        if (validate(user)){
            users.put(user.getId(), user);
        }
        return user;

    }

    @PutMapping
    public User UpdateUser(@RequestBody User user) throws ValidationException {
        if (validate(user)){
            users.replace(user.getId(), user);
        }
        return user;
    }
}
