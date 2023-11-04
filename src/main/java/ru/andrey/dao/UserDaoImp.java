package ru.andrey.dao;

import ru.andrey.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Transactional
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void addUser(User user) {
        if (user == null) {
            logger.warning("user: null");
        } else {
            logger.info(user.toString());
            entityManager.persist(user);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        logger.info("");
        String jpql = "select user from User user";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        List<User> users = query.getResultList();
        // При сохранении даты в MySQL информация об эре теряется.
        // Для сравнения дат до н.э. в поле birthDate должно быть корректное значение даты.
        // Флаг eraBc нужен только для сохранения в MySQL дат до н.э.
        // Поэтому пересчитываем дату с учетом эры.
        users.forEach(u -> u.setEraBc(u.isEraBc()));
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        logger.info("id: " + id);
        User user = entityManager.find(User.class, id);
        if (user != null) {
            // При сохранении даты в MySQL информация об эре теряется.
            // Для сравнения дат до н.э. в поле birthDate должно быть корректное значение даты.
            // Флаг eraBc нужен только для сохранения в MySQL дат до н.э.
            // Поэтому пересчитываем дату с учетом эры.
            user.setEraBc(user.isEraBc());
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            logger.warning("user: null");
        } else {
            logger.info(user.toString());
            entityManager.merge(user);
        }
    }

    @Override
    public void removeUser(User user) {
        if (user == null) {
            logger.warning("user: null");
        } else {
            logger.info(user.toString());
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        }
    }

    @Override
    public void removeUserById(long id) {
        logger.info("id: " + id);
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void removeAllUsers() {
        logger.info("");
        String sql = "truncate table users";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void initTable() {
        logger.info("");
        removeAllUsers();

        //  user1
        Calendar birthDay1 = new GregorianCalendar(1879, Calendar.MARCH, 14);
        User user1 = new User("Альберт", "Эйнштейн", "albert_einstein@gmail.com",
                birthDay1.getTime(), new Date());

        //  user2
        Calendar birthDay2 = new GregorianCalendar(287, Calendar.JANUARY, 1);
        User user2 = new User("Архимед", null, "archimedes@pigeon.org",
                birthDay2.getTime(), new Date());
        user2.setEraBc(true);

        //  user3
        Calendar birthDay3 = new GregorianCalendar(1452, Calendar.APRIL, 15);
        User user3 = new User("Леонардо", "да Винчи", "leonardo_da_vinci@yahoo.com",
                birthDay3.getTime(), new Date());

        //  user4
        Calendar birthDay4 = new GregorianCalendar(1856, Calendar.JULY, 10);
        User user4 = new User("Никола", "Тесла", "nikola_tesla@tesla.edu",
                birthDay4.getTime(), new Date());

        addUser(user1);
        addUser(user2);
        addUser(user3);
        addUser(user4);
    }
}
