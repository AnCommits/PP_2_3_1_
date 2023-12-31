package ru.andrey.dao;

import org.springframework.stereotype.Repository;
import ru.andrey.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

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

    @Override
    public List<User> getAllUsers() {
        logger.info("");
        String hql = "FROM User";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        List<User> users = query.getResultList();
        // Comment in setEraBc
        users.forEach(u -> u.setEraBc(u.isEraBc()));
        return users;
    }

    @Override
    public List<User> getAllUsersSorted(String column) {
        logger.info("column: " + column);

        if (column.equals("id")) {
            return getAllUsers();
        } else {
            String hql;
            if (column.equals("birthDate")) {
                hql = """
                        FROM User user
                        ORDER BY
                        CASE WHEN user.eraBc = true THEN user.birthDate END DESC,
                        CASE WHEN user.eraBc = false THEN user.birthDate END
                        """;
            } else {
                hql = "FROM User user ORDER BY user." + column;
            }
            TypedQuery<User> query = entityManager.createQuery(hql, User.class);
            List<User> users = query.getResultList();
            users.forEach(u -> u.setEraBc(u.isEraBc()));
            return users;
        }
    }

    @Override
    public User getUserById(long id) {
        logger.info("id: " + id);
        User user = entityManager.find(User.class, id);
        if (user != null) {
            // Comment in setEraBc
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
