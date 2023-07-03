package pi.dev.realestate.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.sql.Timestamp;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name="propertyOffers")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String reference;
    String title;
    @Enumerated(EnumType.STRING)
    pi.dev.realestate.entities.EnumType type;
    @Enumerated(EnumType.STRING)
    CategoryType category;
    Double price;
    Double sqm;
    String location;
    int bedrooms;
    int bathrooms;
    @Column(nullable = false, updatable = false)
    Timestamp createdAt;
    @Column(nullable = false)
    Timestamp updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
