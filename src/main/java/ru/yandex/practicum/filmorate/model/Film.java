package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Film {
    private int id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @Size(max = 200)
    private String description;

    @NonNull
    private LocalDate releaseDate;

    @NonNull
    @Positive
    private int duration;
}
