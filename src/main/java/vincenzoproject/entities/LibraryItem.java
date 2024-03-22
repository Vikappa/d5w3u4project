package vincenzoproject.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long isbn; // ASSUMIAMO CHE GLI ISBN SIANO DEI LONG INT

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    public LibraryItem() {
    }

    public LibraryItem(String title, int publicationYear, int numberOfPages) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }
}

