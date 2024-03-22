package vincenzoproject;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vincenzoproject.entities.*;
import vincenzoproject.entities.dao.*;

import java.time.LocalDate;
import java.util.List;

public class Application {
    static Faker faker = new Faker();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("d5w3u4");

    public static <LibraryItem> void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        LibraryItemDAO LIDAO = new LibraryItemDAO(em);
        UserDAO userDAO = new UserDAO(em);
        BookDAO bookDAO = new BookDAO(em);
        MagazineDAO magaDao = new MagazineDAO(em);
        LoanDAO loanDAO = new LoanDAO(em);

        Book testBook = new Book();
        Magazine testMaga = new Magazine();

        testBook.setTitle(faker.book().title());
        testBook.setAuthor(faker.book().author());
        testBook.setPublicationYear(faker.number().numberBetween(1900, 2021));
        testBook.setNumberOfPages(faker.number().numberBetween(1, 1000));
        testBook.setGenre(faker.book().genre());
        bookDAO.addBook(testBook); // AGGIUNTA DI ELEMENTO A CATALOGO


        testMaga.setTitle(faker.book().title());
        testMaga.setPublicationYear(faker.number().numberBetween(1900, 2021));
        testMaga.setNumberOfPages(faker.number().numberBetween(1, 1000));
        testMaga.setRandomPublicationTime();
        magaDao.addMagazine(testMaga);

        Book testBook2 = new Book(); // BOOK DA ELIMINARE, VERIFICARE CHE L'ISBN STAMPATO IN CONSOLE NON ESISTA NEL DB
        testBook2.setTitle(faker.book().title());
        testBook2.setAuthor(faker.book().author());
        testBook2.setPublicationYear(faker.number().numberBetween(1900, 2021));
        testBook2.setNumberOfPages(faker.number().numberBetween(1, 1000));
        testBook2.setGenre(faker.book().genre());
        bookDAO.addBook(testBook2);
        em.clear();
        System.out.println("ISBN LIBRO DA ELIMINARE PER TEST: "+testBook2.getIsbn());
        bookDAO.removeFromLibrary(testBook2.getIsbn()); //RIMOZIONE DI ELEMENTO BY ISBN

//POPOLO IL MAGAZZINO CON TANTI LIBRI PER TEST FUNZIONI RICERCA
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setAuthor(faker.book().author());
                book.setPublicationYear(faker.number().numberBetween(1900, 2021));
                book.setNumberOfPages(faker.number().numberBetween(1, 1000));
                book.setGenre(faker.book().genre());
                bookDAO.addBook(book);
            } else {
                Magazine magazine = new Magazine();
                magazine.setTitle(faker.book().title());
                magazine.setPublicationYear(faker.number().numberBetween(1900, 2021));
                magazine.setNumberOfPages(faker.number().numberBetween(1, 1000));
                magazine.setRandomPublicationTime();
                magaDao.addMagazine(magazine);
            }
        }

        //ARRAY DI RISULTATI PER FUNZIONE RICERCA LIBRO
        System.out.println("");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RICERCA PER ANNO CON PARAMETRO 2005:");
        List<LibraryItem> resList = (List<LibraryItem>) LIDAO.findByPublicationYear(2005);
        resList.forEach(System.out::println);
        System.out.println("");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RICERCA PER AUTORE CON PARAMETRO 'Tolkien':");
        List<Book> resList2 = bookDAO.findByAuthor("Tolkien");
        resList2.forEach(System.out::println);
        System.out.println("");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RICERCA PER TITOLO/KEYWORD CON PARAMETRO 'the':");
        List<LibraryItem> resList3 = (List<LibraryItem>) LIDAO.findByKeyword("the");
        resList3.forEach(System.out::println);

        System.out.println("");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("RICERCA LIBRI DEI PRESTITI DELL'UTENTE CREATO COME MOCKUP A INIZIO ESERCIZIO:");
        System.out.println("DI SEGUITO CONFRONTO I VALORI HARDCODED CON I RISULTATI DI RICERCA:");
        System.out.println("LIBRO MOCKUP: " + testBook);


        User user1 = new User();
        user1.setCardNumber(faker.idNumber().valid());
        user1.setFirstName(faker.name().firstName());
        user1.setLastName(faker.name().lastName());
        user1.setBirthDate(faker.date().birthday());
        userDAO.addUser(user1);
        System.out.println("RISULTATI RICERCA IN BASE ALLA PK DI USER1:");
        System.out.println("USER1:" + user1);


        User user2 = new User();
        user2.setCardNumber(faker.idNumber().valid());
        user2.setFirstName(faker.name().firstName());
        user2.setLastName(faker.name().lastName());
        userDAO.addUser(user2);

        Loan loan1 = new Loan();
        loan1.setUser(user1);
        loan1.setLibraryItem(testBook);
        loan1.setStartDate(LocalDate.now());
        loan1.updateExpectedReturnDate();
        loanDAO.addLoan(loan1);

        Loan loan2 = new Loan();
        loan2.setUser(user2);
        loan2.setLibraryItem(testMaga);
        loan2.setStartDate(LocalDate.now());
        loan2.updateExpectedReturnDate();
        loanDAO.addLoan(loan2);
        loanDAO.returnLoan(loan2.getId());

        System.out.println("");
        LIDAO.findBooksLoanedByUser(user1.getCardNumber()).forEach(System.out::println);

        em.close();
        emf.close();
    }



}
