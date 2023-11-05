package ru.andrey.util;

import org.springframework.stereotype.Component;
import ru.andrey.dao.UserDao;
import ru.andrey.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class InitTable {

    private final UserDao userDao;

    public InitTable(UserDao userDao) {
        this.userDao = userDao;
    }

    public void initTable() {
        userDao.removeAllUsers();

        //  user1
        User user1 = new User("Альберт", "Эйнштейн", "albert_einstein@gmail.com",
                new GregorianCalendar(1879, Calendar.MARCH, 14).getTime());

        //  user2
        User user2 = new User("Архимед", null, "archimedes@pigeon.org",
                new GregorianCalendar(287, Calendar.JANUARY, 1).getTime());
        user2.setEraBc(true);

        //  user3
        User user3 = new User("Леонардо", "да Винчи", "leonardo_da_vinci@yahoo.com",
                new GregorianCalendar(1452, Calendar.APRIL, 15).getTime());

        //  user4
        User user4 = new User("Никола", "Тесла", "nikola_tesla@tesla.edu",
                new GregorianCalendar(1856, Calendar.JULY, 10).getTime());

        //  user5
        User user5 = new User();
        user5.setFirstName("Неизвестный");

        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        userDao.addUser(user4);
        userDao.addUser(user5);
    }
}
