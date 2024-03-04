package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Film {

    private final int id;
    @NotBlank
    private final String name;
    @Size(min = 1, max = 200)
    private final String description;
    @NotNull
    private final LocalDate releaseDate;
    @Min(1)
    private final int duration;
}
