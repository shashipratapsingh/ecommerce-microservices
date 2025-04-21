package OrderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantity;
    private double totalPrice;
    private String status; // Pending, Shipped, Delivered, Canceled

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
