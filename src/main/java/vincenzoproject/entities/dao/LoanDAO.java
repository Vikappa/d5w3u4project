package vincenzoproject.entities.dao;

import jakarta.persistence.EntityManager;
import vincenzoproject.entities.Loan;

import java.time.LocalDate;
import java.util.List;

public class LoanDAO {

    private EntityManager entityManager;

    public LoanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addLoan(Loan loan) {
        entityManager.getTransaction().begin();
        entityManager.persist(loan);
        entityManager.getTransaction().commit();
    }

    public Loan findLoanById(Long id) {
        return entityManager.find(Loan.class, id);
    }

    public void returnLoan(Long id) { //SETTA LA FINE DEL PRESTITO EFFETTIVA ALLA DATA CORRENTE
        Loan loan = findLoanById(id);
        if (loan != null) {
            entityManager.getTransaction().begin();
            loan.setActualReturnDate(LocalDate.now());
            entityManager.merge(loan);
            entityManager.getTransaction().commit();
        } else {
            throw new IllegalArgumentException("No loan found with id: " + id);
        }
    }

    public List<Loan> findAllLoans() {
        return entityManager.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    public List<Loan> findLoansByUserCardNumber(String cardNumber) {
        return entityManager.createQuery("SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber", Loan.class)
                .setParameter("cardNumber", cardNumber)
                .getResultList();
    }
    public List<Loan> findOverdueLoans() {
        LocalDate today = LocalDate.now();
        return entityManager.createQuery(
                        "SELECT l FROM Loan l WHERE l.endDate < :today AND l.actualReturnDate IS NULL", Loan.class)
                .setParameter("today", today)
                .getResultList();
    }
}
