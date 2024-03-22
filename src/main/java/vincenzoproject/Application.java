package vincenzoproject;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vincenzoproject.entities.Book;
import vincenzoproject.entities.Magazine;
import vincenzoproject.entities.dao.BookDAO;
import vincenzoproject.entities.dao.LibraryItemDAO;
import vincenzoproject.entities.dao.MagazineDAO;

public class Application {
    static Faker faker = new Faker();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("d5w3u4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        BookDAO bookDAO = new BookDAO(em);
        MagazineDAO magaDao = new MagazineDAO(em);

        Book testBook = new Book();
        Magazine testMaga = new Magazine();

        testBook.setTitle(faker.book().title());
        testBook.setAuthor(faker.book().author());
        testBook.setPublicationYear(faker.number().numberBetween(1900, 2021));
        testBook.setNumberOfPages(faker.number().numberBetween(1, 1000));
        testBook.setGenre(faker.book().genre());
        bookDAO.addBook(testBook);

        em.clear();

        testMaga.setTitle(faker.book().title());
        testMaga.setPublicationYear(faker.number().numberBetween(1900, 2021));
        testMaga.setNumberOfPages(faker.number().numberBetween(1, 1000));
        testMaga.setRandomPublicationTime();
        magaDao.addMagazine(testMaga);

        em.clear();

        em.close();
        emf.close();
    }



}
