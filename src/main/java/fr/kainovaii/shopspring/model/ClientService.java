package fr.kainovaii.shopspring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_service")
public class ClientService
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false, length = 150)
    private String serviceName;

    @Column(nullable = false)
    private double price;

    @Column
    private Integer duration;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long status;

    @Column(nullable = false, length = 150)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    public ClientService() {
        this.createdAt = LocalDateTime.now();
    }

    public ClientService(Long id, Long productId, String serviceName, double price, Integer duration, LocalDateTime createdAt, Long status, String type, User user)
    {
        this.id = id;
        this.productId = productId;
        this.serviceName = serviceName;
        this.price = price;
        this.duration = duration;
        this.createdAt = createdAt;
        this.status = status;
        this.type = type;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getServiceName() { return serviceName; }

    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getStatus() { return status; }
    public void setStatus(Long status) { this.status = status;}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public long getUser() { return user.getId(); }
    public void setUser(User user) { this.user = user; }
}
