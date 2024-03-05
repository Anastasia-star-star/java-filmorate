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
@RequiredArgsConstructor
@Builder
public class User {
    private int id;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String login;

    @Builder.Default
    private String name = "";

    @NotNull
    private LocalDate birthday;
}
