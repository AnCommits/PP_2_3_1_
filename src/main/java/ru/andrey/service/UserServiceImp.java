package ru.andrey.service;

import org.springframework.transaction.annotation.Transactional;
import ru.andrey.dao.UserDao;
import ru.andrey.model.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsersSorted(String  column) {
        return userDao.getAllUsersSorted(column);
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

    @Transactional
    @Override
    public void initTable() {

        User user1 = new User("Альберт", "Эйнштейн", "albert_einstein@gmail.com",
                new GregorianCalendar(1879, Calendar.MARCH, 14).getTime());

        User user2 = new User("Архимед", null, "archimedes@pigeon.org",
                new GregorianCalendar(287, Calendar.JANUARY, 1).getTime());
        user2.setEraBc(true);

        User user3 = new User("Леонардо", "да Винчи", "leonardo_da_vinci@yahoo.com",
                new GregorianCalendar(1452, Calendar.APRIL, 15).getTime());

        User user4 = new User("Никола", "Тесла", "nikola_tesla@tesla.edu",
                new GregorianCalendar(1856, Calendar.JULY, 10).getTime());

        User user5 = new User();
        user5.setFirstName("Неизвестный");

        removeAllUsers();

        addUser(user1);
        addUser(user2);
        addUser(user3);
        addUser(user4);
        addUser(user5);
    }
}
