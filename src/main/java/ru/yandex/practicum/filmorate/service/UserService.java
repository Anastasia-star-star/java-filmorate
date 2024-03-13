package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service

public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage useStorage) {
        this.userStorage = useStorage;
    }

    public User addInFriends(Integer id, Integer friendId) throws ValidationException {
        Set<Integer> setByUser1 = userStorage.getUsers().get(id).getFriends();
        Set<Integer> setByUser2 = userStorage.getUsers().get(friendId).getFriends();
        if (setByUser1 == null) {
            setByUser1 = new HashSet<>();
        }
        if (setByUser2 == null) {
            setByUser2 = new HashSet<>();
        }
        setByUser1.add(friendId);
        userStorage.getUsers().get(id).setFriends(setByUser1);

        setByUser2.add(id);
        userStorage.getUsers().get(friendId).setFriends(setByUser2);
        return userStorage.getUsers().get(id);
    }

    public User deleteFromFriend(Integer id, Integer friendId) throws ValidationException {
        Set<Integer> setByUser1 = userStorage.getUsers().get(id).getFriends();
        Set<Integer> setByUser2 = userStorage.getUsers().get(friendId).getFriends();

        setByUser1.remove(friendId);
        userStorage.getUsers().get(id).setFriends(setByUser1);

        setByUser2.remove(id);
        userStorage.getUsers().get(friendId).setFriends(setByUser2);
        return userStorage.getUsers().get(id);
    }

    public ArrayList<User> getCommonFriends(Integer id, Integer otherId) {
        Set<Integer> idFriendsByUsers1 = userStorage.getUsers().get(id).getFriends();
        Set<Integer> idFriendsByUsers2 = userStorage.getUsers().get(otherId).getFriends();
        Set<Integer> intersection = new HashSet<Integer>(idFriendsByUsers1);
        intersection.retainAll(idFriendsByUsers2);

        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : intersection) {
            listUsers.add(userStorage.getUsers().get(ind));
        }
        return listUsers;
    }
}
