package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film putLike(Integer id, Integer userId) throws ValidationException {
        Set<Integer> userLikes = new HashSet<>();
        if (filmStorage.getFilms().get(id).getUserLikes() != null) {
            userLikes = filmStorage.getFilms().get(id).getUserLikes();
        }
        userLikes.add(userId);
        filmStorage.getFilms().get(id).setUserLikes(userLikes);
        return filmStorage.getFilms().get(id);
    }

    public Film deleteLike(Integer id, Integer userId) throws ValidationException {
        Set<Integer> userLikes = new HashSet<>();
        if (filmStorage.getFilms().get(id).getUserLikes() == null) {
            log.info("Лайков для удаления нет");
            throw new ValidationException("");
        }
        userLikes = filmStorage.getFilms().get(id).getUserLikes();
        userLikes.remove(userId);
        filmStorage.getFilms().get(id).setUserLikes(userLikes);
        return filmStorage.getFilms().get(id);
    }

    public List<Film> getTopPopularFilms(Integer count) {
        if (count < 0) {
            log.error("Количество фильмов не должно быть отрицательным");
        }
        return filmStorage.getFilms().stream()
                .sorted((f1, f2) -> f2.getUserLikes().size() - f1.getUserLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }
}
