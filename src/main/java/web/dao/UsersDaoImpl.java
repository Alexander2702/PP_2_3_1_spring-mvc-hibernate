package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UsersDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public UsersDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(int id) {
//        return entityManager.createQuery("FROM User WHERE id=" + id, User.class).getSingleResult();
        return entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void removeUserById(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}