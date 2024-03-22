package vincenzoproject.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


 enum publicationTime {
    WEEKLY, MONTHLY, HALF_YEARLY
}

@Entity
@DiscriminatorValue("MAGAZINE")
public class Magazine extends LibraryItem {

    private publicationTime publicationTime;

    public Magazine(vincenzoproject.entities.publicationTime publicationTime) {
        this.publicationTime = publicationTime;
    }

    public Magazine(String title, int publicationYear, int numberOfPages, vincenzoproject.entities.publicationTime publicationTime) {
        super(title, publicationYear, numberOfPages);
        this.publicationTime = publicationTime;
    }

    public Magazine() {
        super();
    }

    public void setRandomPublicationTime() {
        Faker faker = new Faker();
        this.publicationTime = publicationTime.values()[faker.number().numberBetween(0, publicationTime.values().length - 1)];
    }

    public vincenzoproject.entities.publicationTime getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(vincenzoproject.entities.publicationTime publicationTime) {
        this.publicationTime = publicationTime;
    }
}
