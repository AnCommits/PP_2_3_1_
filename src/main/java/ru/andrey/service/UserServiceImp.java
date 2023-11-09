package ru.andrey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrey.dao.UserDao;
import ru.andrey.model.User;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsersSorted(String column) {
        List<User> users;
        if (column.equals("id")) {
            users = userDao.getAllUsers();
        } else if (column.equals("birthDate")) {
            users = userDao.getAllUsers();
            users.sort(User.userComparatorByBirthDate);
        } else {
            users = userDao.getAllUsersSorted(column);
        }
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public void removeAllUsers() {
        userDao.removeAllUsers();
    }
}
