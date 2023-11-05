package ru.andrey.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.andrey.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        // Комментарий в setEraBc
        users.forEach(u -> u.setEraBc(u.isEraBc()));
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        logger.info("id: " + id);
        User user = entityManager.find(User.class, id);
        if (user != null) {
            // Комментарий в setEraBc
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
}
