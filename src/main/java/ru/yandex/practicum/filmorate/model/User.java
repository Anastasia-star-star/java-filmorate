package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @NotBlank
    private String email;
    @NotBlank
    private String login;
    private String name;
    private LocalDate birthday;
}
