package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.Magazine;

import java.util.List;

public class MagazineDAO extends LibraryItemDAO {
    public MagazineDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void addMagazine(Magazine magazine) {
        EntityManager em = entityManager;
        em.getTransaction().begin();
        em.persist(magazine);
        em.getTransaction().commit();
    }

    public List<Magazine> findMagazinesByPublicationYear(int year) {
        EntityManager em = entityManager;
        return em.createQuery("SELECT m FROM Magazine m WHERE m.publicationYear = :year", Magazine.class)
                .setParameter("year", year)
                .getResultList();
    }
}
