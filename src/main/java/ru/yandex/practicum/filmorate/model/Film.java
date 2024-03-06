package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private int id;

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @NotBlank(message = "Описание не должно быть пустым")
    @Size(max = 200, message = "Описание не должно быть более 200 символов")
    private String description;

    @NotNull(message = "Параметры даты должны быть заданы")
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть больше 0")
    private int duration;
}
