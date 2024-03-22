package vincenzoproject.entities;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends LibraryItem {
    @Column(nullable = true)
    private String author;

    @Column(nullable = true)
    private String genre;

    public Book() {
        super();
    }

    public Book(String title, int publicationYear, int numberOfPages, String author, String genre) {
        super(title, publicationYear, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", NumberOfPages='" + getNumberOfPages() + '\'' +
                "ISBN=" + getIsbn() +
                '}';
    }
}
