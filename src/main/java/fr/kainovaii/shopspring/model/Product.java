package fr.kainovaii.shopspring.model;

import fr.kainovaii.shopspring.converter.JsonConverter;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @Column(columnDefinition = "TEXT") // SQLite TEXT
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> metadata;

    public Product() {}

    public Product(Long id, String name, double price, Map<String, Object> metadata)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.metadata = metadata;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
}
