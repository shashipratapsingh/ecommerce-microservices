package ProductService.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;

    private String createdBy;
    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdOn = now;
        this.updatedOn = now;
        this.createdBy = "admin";
        this.updatedBy = "admin";
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
        this.updatedBy = "admin";
    }
}
