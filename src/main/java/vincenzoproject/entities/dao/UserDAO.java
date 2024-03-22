package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.User;

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

}
