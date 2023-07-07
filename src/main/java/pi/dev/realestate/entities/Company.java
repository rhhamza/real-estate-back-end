package pi.dev.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.EnumType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    private Integer id;
    private String name;
    private String address;
    private String description;
    private int phone;
    private String email;
    private StatusType status;

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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="company")
    private Set<Order> orders = new HashSet<>();

    @OneToOne(mappedBy="company")
    private CompanyImage image ;
}
