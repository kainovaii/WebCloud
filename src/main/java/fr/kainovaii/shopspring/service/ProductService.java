package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return productRepository.existsById(id);
    }

    public long count() {
        return productRepository.count();
    }
}