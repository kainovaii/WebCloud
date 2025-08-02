package fr.kainovaii.shopspring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String distribution;
    private String status; // PENDING, PAID, PROVISIONED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Order() {}

    public Order(Long userId, String distribution, String status)
    {
        this.userId = userId;
        this.distribution = distribution;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDistribution() { return distribution; }
    public void setDistribution(String distribution) { this.distribution = distribution; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
