package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}