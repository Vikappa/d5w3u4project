package vincenzoproject.entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_number", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private LibraryItem libraryItem;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }



    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Loan(User user, LibraryItem libraryItem, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate) {
        this.user = user;
        this.libraryItem = libraryItem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedReturnDate = startDate.plusDays(30);
        this.actualReturnDate = actualReturnDate;
    }
    public Loan(User user, LibraryItem libraryItem, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.libraryItem = libraryItem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedReturnDate = startDate.plusDays(30);
    }

    public void updateExpectedReturnDate() {
        this.expectedReturnDate = this.startDate.plusDays(30);
    }
}
