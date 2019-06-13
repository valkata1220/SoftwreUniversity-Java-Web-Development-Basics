package panda.repository;

import panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("" +
                "SELECT u " +
                "FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User findById(String id) {
        return this.entityManager.createQuery("" +
                "SELECT u " +
                "FROM User u " +
                "WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public long size() {
        return this.entityManager.createQuery("" +
                "SELECT count(u) " +
                "FROM User u", Long.class)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        try {
            User user = this.entityManager.createQuery("" +
                    "SELECT u " +
                    "FROM User u " +
                    "WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
