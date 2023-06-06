package pi.dev.realestate.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String address;
    private String description;
    private int phone;
    private String email;
    private String logo;
    private StatusType status;
    @Column(nullable = false, updatable = false)
    java.sql.Timestamp createdAt;
    @Column(nullable = false)
    java.sql.Timestamp updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new java.sql.Timestamp(System.currentTimeMillis());
        updatedAt = new java.sql.Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="orders")
    private Set<Order> TpCorrections;
}
