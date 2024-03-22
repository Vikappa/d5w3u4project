package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.Book;

import java.util.List;

public class BookDAO extends LibraryItemDAO {

    public BookDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Book> findBooksByAuthor(String author) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }
    public void addBook(Book book) {
        EntityManager em = entityManager;
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    public List<Book> findBooksByPublicationYear(int year) {
        EntityManager em = entityManager;
        return em.createQuery("SELECT b FROM Book b WHERE b.publicationYear = :year", Book.class)
                .setParameter("year", year)
                .getResultList();
    }
    public List<Book> findAllBooks() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
    public List<Book> findByAuthor(String author) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

}