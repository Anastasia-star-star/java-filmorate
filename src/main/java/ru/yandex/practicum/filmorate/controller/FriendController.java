package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendService;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/users")
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addInFriends(@PathVariable("id") Integer id,
                             @PathVariable("friendId") Integer friendId) {
        return friendService.addInFriends(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User deleteFromFriends(@PathVariable("id") Integer id,
                                  @PathVariable("friendId") Integer friendId) {
        return friendService.deleteFromFriend(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public ArrayList<User> getCommonFriends(@PathVariable("id") Integer id,
                                            @PathVariable("otherId") Integer otherId) {
        return friendService.getCommonFriends(id, otherId);
    }
}
