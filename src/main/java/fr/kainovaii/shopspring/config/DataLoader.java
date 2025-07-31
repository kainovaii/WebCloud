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
            repository.save(new Product(null,"vps-lxc", "std-lxc-2", 5.99, Map.of(
                    "vCore", 2,
                    "RAM", 4
            )));
        };
    }
}