package fr.kainovaii.shopspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String distribution;
    private String status; // PENDING, PAID, PROVISIONED

    public Order() {}

    public Order(Long userId, String distribution, String status) {
        this.userId = userId;
        this.distribution = distribution;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDistribution() { return distribution; }
    public void setDistribution(String distribution) { this.distribution = distribution; }

    public String getStatus() {  return status; }
    public void setStatus(String status) { this.status = status; }

    // Temp test
    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId=" + userId +
            ", distribution='" + distribution + '\'' +
            ", status='" + status + '\'' +
        '}';
    }
}
