package fr.kainovaii.shopspring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            /*
            repository.save(new Product(1L,"vps-lxc", "std-lxc-1", 3.99, Map.of(
                    "core", 1,
                    "ram", 2,
                    "storage", 500,
                    "connection", 300
            )));
            repository.save(new Product(2L,"vps-lxc", "std-lxc-2", 5.99, Map.of(
                    "core", 2,
                    "ram", 4,
                    "storage", 500,
                    "connection", 300
            )));
            repository.save(new Product(3L,"vps-lxc", "std-lxc-3", 7.99, Map.of(
                    "core", 4,
                    "ram", 8,
                    "storage", 1000,
                    "connection", 300
            )));
             */
        };
    }
}