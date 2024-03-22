package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import vincenzoproject.entities.User;

import java.util.List;

public class UserDAO {

    private EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public User findUserByCardNumber(String cardNumber) {
        return entityManager.find(User.class, cardNumber);
    }

    public void deleteUser(String cardNumber) {
        User user = findUserByCardNumber(cardNumber);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findUserByName(String firstName, String lastName) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName", User.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
