package ru.javamentor.springmvc.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.javamentor.springmvc.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public User getUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.getReference(User.class, id);
        entityManager.remove(user);
    }
}
