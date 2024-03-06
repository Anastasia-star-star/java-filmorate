package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor

public class User {
    private int id;

    @NotEmpty
    @Email(message = "Почта не должна быть пустой")
    private String email;

    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    private String name;

    @NotNull(message = "Дата должна быть верно заполнена")
    private LocalDate birthday;
}
