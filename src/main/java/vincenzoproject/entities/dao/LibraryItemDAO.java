package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.LibraryItem;
import vincenzoproject.entities.Loan;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemDAO {

    protected static EntityManager entityManager;

    public LibraryItemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(LibraryItem item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public LibraryItem findById(Long isbn) {
        return entityManager.find(LibraryItem.class, isbn);
    }

    public List<LibraryItem> findAll() {
        return entityManager.createQuery("SELECT li FROM LibraryItem li", LibraryItem.class).getResultList();
    }

    public List<LibraryItem> findAllLibraryItems() {
        BookDAO bookDao = new BookDAO(entityManager);
        MagazineDAO magazineDao = new MagazineDAO(entityManager);

        List<LibraryItem> items = new ArrayList<>();
        items.addAll(bookDao.findAllBooks());
        items.addAll(magazineDao.findAllMagazines());
        return items;
    }

    public void removeFromLibrary(Long isbn) {
        entityManager.getTransaction().begin();
        LibraryItem item = entityManager.find(LibraryItem.class, isbn);
        if (item != null) {
            entityManager.remove(item);
        } else {
            System.out.println("BOOK " + isbn + " NOT FOUND");
        }
        entityManager.getTransaction().commit();
    }

    public List<LibraryItem> findByPublicationYear(int year) {
        return entityManager.createQuery("SELECT li FROM LibraryItem li WHERE li.publicationYear = :year", LibraryItem.class)
                .setParameter("year", year)
                .getResultList();
    }


    public List<LibraryItem> findByKeyword(String keyword) {
        // La keyword viene circondata da % per indicare una ricerca "contiene"
        String searchPattern = "%" + keyword + "%";
        return entityManager.createQuery("SELECT li FROM LibraryItem li WHERE li.title LIKE :keyword", LibraryItem.class)
                .setParameter("keyword", searchPattern)
                .getResultList();
    }

    public List<LibraryItem> findBooksLoanedByUser(String cardNumber) {
        return entityManager.createQuery(
                        "SELECT l.libraryItem FROM Loan l WHERE l.user.cardNumber = :cardNumber", LibraryItem.class)
                .setParameter("cardNumber", cardNumber)
                .getResultList();
    }



}
