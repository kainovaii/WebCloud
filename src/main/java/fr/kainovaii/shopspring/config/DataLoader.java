package fr.kainovaii.shopspring.config;

import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            repository.save(new Product(null, "Wireless Mouse", 29.99));
            repository.save(new Product(null, "Mechanical Keyboard", 89.99));
            repository.save(new Product(null, "USB-C Hub", 49.99));
            repository.save(new Product(null, "4K Monitor", 299.99));
            repository.save(new Product(null, "Bluetooth Headphones", 119.99));
            repository.save(new Product(null, "Webcam 1080p", 59.99));
            repository.save(new Product(null, "Ergonomic Chair", 199.99));
            repository.save(new Product(null, "Laptop Stand", 39.99));
            repository.save(new Product(null, "External SSD 1TB", 129.99));
            repository.save(new Product(null, "Gaming Controller", 69.99));
        };
    }
}