package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.users.UserStorage;

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

    public User addInFriends(Integer id, Integer friendId) {
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
        return userStorage.getHashMapUsers().get(id);
    }

    public User deleteFromFriend(Integer id, Integer friendId) {
        Set<Integer> setByUser1 = userStorage.getHashMapUsers().get(id).getFriends();
        Set<Integer> setByUser2 = userStorage.getHashMapUsers().get(friendId).getFriends();
        if (setByUser1 == null) {
            setByUser1 = new HashSet<>();
        }
        if (setByUser2 == null) {
            setByUser2 = new HashSet<>();
        }

        setByUser1.remove(friendId);
        userStorage.getHashMapUsers().get(id).setFriends(setByUser1);

        setByUser2.remove(id);
        userStorage.getHashMapUsers().get(friendId).setFriends(setByUser2);
        return userStorage.getHashMapUsers().get(id);
    }

    public ArrayList<User> getCommonFriends(Integer id, Integer otherId) {
        Set<Integer> idFriendsByUsers1 = userStorage.getHashMapUsers().get(id).getFriends();
        Set<Integer> idFriendsByUsers2 = userStorage.getHashMapUsers().get(otherId).getFriends();
        Set<Integer> intersection = new HashSet<Integer>(idFriendsByUsers1);
        intersection.retainAll(idFriendsByUsers2);

        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : intersection) {
            listUsers.add(userStorage.getHashMapUsers().get(ind));
        }
        return listUsers;
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public ArrayList<User> getUsers() {
        return userStorage.getUsers();
    }

    public ArrayList<User> getFriendsById(Integer id) {
        return userStorage.getFriendsById(id);
    }

    public User getUserById(Integer id) {
        return userStorage.getUserById(id);
    }
}
