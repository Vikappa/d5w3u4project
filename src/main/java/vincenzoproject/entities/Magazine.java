package vincenzoproject.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MAGAZINE")
public class Magazine extends LibraryItem {
    private String name;

    // Costruttori, getter e setter
}
