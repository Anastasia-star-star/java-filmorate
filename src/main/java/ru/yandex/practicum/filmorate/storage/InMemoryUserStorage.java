package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Getter
@Component
public class InMemoryUserStorage implements UserStorage {
    private int nextId = 1;
    private final HashMap<Integer, User> users = new HashMap<>();
    private static final LocalDate DATE = LocalDate.now();

    public boolean validate(User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
            log.info("В логине присутствуют пробелы");
            throw new ValidationException("В логине присутствуют пробелы");
        } else if (user.getBirthday().isAfter(DATE)) {
            log.info("День рождения пользователя в будущем");
            throw new ValidationException("День рождения пользователя в будущем");
        } else {
            if (user.getName() == null || user.getName().isBlank()) {
                user.setName(user.getLogin());
                log.info("Имя пользователя пустое, будет использоваться логин");
            }
            log.info("Инициализировано добавление фильма");
            return true;
        }
    }

    public ArrayList<User> getFriendsById(Integer id) {
        if (users.get(id).getFriends() == null) {
        }
        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : users.get(id).getFriends()) {
            listUsers.add(users.get(ind));
        }
        return listUsers;
    }

    public ArrayList<User> getCommonFriends(Integer id, Integer otherId) {
        Set<Integer> idFriendsByUsers1 = users.get(id).getFriends();
        Set<Integer> idFriendsByUsers2 = users.get(otherId).getFriends();
        Set<Integer> intersection = new HashSet<Integer>(idFriendsByUsers1);
        intersection.retainAll(idFriendsByUsers2);

        ArrayList<User> listUsers = new ArrayList<>();
        for (int ind : intersection) {
            listUsers.add(users.get(ind));
        }
        return listUsers;
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User createUser(User user) throws ValidationException {
        if (validate(user)) {
            user.setId(nextId++);
            users.put(user.getId(), user);
        }
        return user;
    }

    @Override
    public User deleteUser(User user) throws ValidationException {
        //
        return user;
    }

    public User updateUser(User user) throws ValidationException {
        if (validate(user)) {
            if (users.containsKey(user.getId())) {
                users.replace(user.getId(), user);
            } else {
                log.info("id don't exist");
                throw new ValidationException("id don't exist");
            }
        }
        return user;
    }

    public User deleteFromFriend(Integer id, Integer friendId) throws ValidationException {
        Set<Integer> setByUser1 = users.get(id).getFriends();
        Set<Integer> setByUser2 = users.get(friendId).getFriends();

        setByUser1.remove(friendId);
        users.get(id).setFriends(setByUser1);

        setByUser2.remove(id);
        users.get(friendId).setFriends(setByUser2);
        return users.get(id);
    }
}
