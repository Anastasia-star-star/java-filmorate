package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class Film {

    private long id;

    @NonNull
    @NotBlank(message = "Ошибка! Название не может быть пустым.")
    private String name;

    @NonNull
    private String description;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive(message = "Ошибка! Продолжительность фильма должна быть положительной.")
    private int duration;

    private int rate;
    private Set<Long> likes = new HashSet<>();
    private Set<Genre> genres = new TreeSet<>(Comparator.comparingLong(Genre::getId));
    @JsonSetter
    public  void setGenres(Set<Genre> genres){
        this.genres.clear();
        this.genres.addAll(genres);
    }
    private MPA mpa;

}