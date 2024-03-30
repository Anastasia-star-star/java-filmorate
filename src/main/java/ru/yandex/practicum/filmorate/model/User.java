package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class User {
    private int id;

    @NotEmpty(message = "Почта не должна быть пустой")
    @Email
    private String email;

    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    private String name;

    private LocalDate birthday;

    private Set<Integer> friends = new HashSet<>();
}
