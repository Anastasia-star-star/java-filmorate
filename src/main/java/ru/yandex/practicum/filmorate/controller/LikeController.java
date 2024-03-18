package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.LikeService;

@RestController
@Slf4j
@RequestMapping("/films")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PutMapping("/{id}/like/{userId}")
    public Film putLike(@PathVariable("id") Integer id,
                        @PathVariable("userId") Integer userId) {
        return likeService.putLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Film deleteLike(@PathVariable("id") Integer id,
                           @PathVariable("userId") Integer userId) {
        return likeService.deleteLike(id, userId);
    }
}
