package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.LibraryItem;

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

}
