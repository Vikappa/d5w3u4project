package vincenzoproject.entities;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends LibraryItem {
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    public Book() {
        super();
    }


}
