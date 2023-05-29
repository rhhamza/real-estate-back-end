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
    String name;
    @Enumerated(EnumType.STRING)
    EnumType type;
    @Enumerated(EnumType.STRING)
    CategoryType category;
    Double price;
    Double sqm;
    String location;
    int bedrooms;
    int bathrooms;
    Timestamp createdAt;
    Timestamp updatedAt;
}
