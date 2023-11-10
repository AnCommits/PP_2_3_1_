package ru.andrey.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.andrey.model.User;
import ru.andrey.service.UserService;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class Init implements InitializingBean {

    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {

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

        User user6 = new User("a1", "f1", "",
                new GregorianCalendar(99, Calendar.JANUARY, 2).getTime());
        user6.setEraBc(true);

        User user7 = new User("a2", "f1", "",
                new GregorianCalendar(99, Calendar.JANUARY, 2).getTime());
        user7.setEraBc(true);

        userService.removeAllUsers();

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);
        userService.addUser(user6);
        userService.addUser(user7);
    }
}
