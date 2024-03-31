package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.films.FilmStorage;
import ru.yandex.practicum.filmorate.storage.users.UserStorage;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class LikeService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public LikeService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film putLike(Integer id, Integer userId) {
        if (!filmStorage.getHashMapFilms().containsKey(id)) {
            log.error("Передан несуществующий id фильма");
            throw new ObjectDoNotExistException("Передан несуществующий id фильма");
        }
        if (!userStorage.getHashMapUsers().containsKey(userId)) {
            log.error("Передан несуществующий id пользователя");
            throw new ObjectDoNotExistException("Передан несуществующий id пользователя");
        }
        Set<Integer> userLikes = new HashSet<>();
        if (filmStorage.getHashMapFilms().get(id).getUserLikes() != null) {
            userLikes = filmStorage.getHashMapFilms().get(id).getUserLikes();
        }
        userLikes.add(userId);
        filmStorage.getHashMapFilms().get(id).setUserLikes(userLikes);
        log.info("Инициализированно добавление лайка");
        return filmStorage.getHashMapFilms().get(id);
    }

    public Film deleteLike(Integer id, Integer userId) {
        if (!filmStorage.getHashMapFilms().containsKey(id)) {
            log.error("Передан несуществующий id фильма");
            throw new ObjectDoNotExistException("Передан несуществующий id фильма");
        }
        if (!userStorage.getHashMapUsers().containsKey(userId)) {
            log.error("Передан несуществующий id пользователя");
            throw new ObjectDoNotExistException("Передан несуществующий id пользователя");
        }
        Set<Integer> userLikes;
        if (filmStorage.getHashMapFilms().get(id).getUserLikes() == null) {
            log.error("Лайков для удаления нет");
            throw new ValidationException("");
        }
        userLikes = filmStorage.getHashMapFilms().get(id).getUserLikes();
        userLikes.remove(userId);
        filmStorage.getHashMapFilms().get(id).setUserLikes(userLikes);
        return filmStorage.getHashMapFilms().get(id);
    }
}
