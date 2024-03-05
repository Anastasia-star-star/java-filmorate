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
    @Email
    private String email;

    @NotBlank
    private String login;

    private String name;

    @NotNull
    private LocalDate birthday;
}
