package pi.dev.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private LocalDate startDate;

    private LocalDate endDate;
    private StatusType status;
    private int price;
    @Column(nullable = false, updatable = false)
    Timestamp createdAt;
    @Column(nullable = false)
    Timestamp updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt= new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @ManyToOne
    Company company;



}

