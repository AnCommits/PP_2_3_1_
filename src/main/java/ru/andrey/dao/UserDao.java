package ru.andrey.dao;

import ru.andrey.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getAllUsersSorted(String column);

    User getUserById(long id);

    void updateUser(User user);

    void removeUser(User user);

    void removeUserById(long id);

    void removeAllUsers();
}
