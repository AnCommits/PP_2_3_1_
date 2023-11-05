package ru.andrey.service;

import ru.andrey.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(long id);

    void updateUser(User user);

    void removeUser(User user);

    void removeUserById(long id);

    void removeAllUsers();
}
