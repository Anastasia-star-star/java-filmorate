package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectDoNotExistException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.users.UserStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class FriendService {
    private final UserStorage userStorage;

    @Autowired
    public FriendService(UserStorage useStorage) {
        this.userStorage = useStorage;
    }

    public User addInFriends(Integer id, Integer friendId) {
        if (!userStorage.getHashMapUsers().containsKey(id) || !userStorage.getHashMapUsers().containsKey(friendId)) {
            log.error("Передан несуществующий id");
            throw new ObjectDoNotExistException("Передан несуществующий id");
        }

        Set<Integer> setByUser1 = userStorage.getHashMapUsers().get(id).getFriends();
        Set<Integer> setByUser2 = userStorage.getHashMapUsers().get(friendId).getFriends();
        if (setByUser1 == null) {
            setByUser1 = new HashSet<>();
        }
        if (setByUser2 == null) {
            setByUser2 = new HashSet<>();
        }
        setByUser1.add(friendId);
        userStorage.getHashMapUsers().get(id).setFriends(setByUser1);

        setByUser2.add(id);
        userStorage.getHashMapUsers().get(friendId).setFriends(setByUser2);
        log.info("Инициализированно добавление в друзья");
        return userStorage.getHashMapUsers().get(id);
    }

    public User deleteFromFriend(Integer id, Integer friendId) {
        if (!userStorage.getHashMapUsers().containsKey(id) || !userStorage.getHashMapUsers().containsKey(friendId)) {
            log.error("Передан несуществующий id");
            throw new ObjectDoNotExistException("Передан несуществующий id");
        }

        Set<Integer> setByUser = userStorage.getHashMapUsers().get(id).getFriends();
        Set<Integer> setByOtherUser = userStorage.getHashMapUsers().get(friendId).getFriends();
        if (setByUser == null) {
            setByUser = new HashSet<>();
        }
        if (setByOtherUser == null) {
            setByOtherUser = new HashSet<>();
        }

        setByUser.remove(friendId);
        userStorage.getHashMapUsers().get(id).setFriends(setByUser);

        setByOtherUser.remove(id);
        userStorage.getHashMapUsers().get(friendId).setFriends(setByOtherUser);
        log.info("Инициализированно удаление из друзей");
        return userStorage.getHashMapUsers().get(id);
    }

    public ArrayList<User> getCommonFriends(Integer id, Integer otherId) {
        if (!userStorage.getHashMapUsers().containsKey(id) || !userStorage.getHashMapUsers().containsKey(otherId)) {
            log.error("Передан несуществующий id");
            throw new ObjectDoNotExistException("Передан несуществующий id");
        }
        Set<Integer> idFriendsByUser = userStorage.getHashMapUsers().get(id).getFriends();
        Set<Integer> idFriendsByOtherUser = userStorage.getHashMapUsers().get(otherId).getFriends();
        Set<Integer> intersection = new HashSet<Integer>(idFriendsByUser);
        intersection.retainAll(idFriendsByOtherUser);

        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : intersection) {
            listUsers.add(userStorage.getHashMapUsers().get(ind));
        }
        log.info("Передан список общих друзей");
        return listUsers;
    }
}
