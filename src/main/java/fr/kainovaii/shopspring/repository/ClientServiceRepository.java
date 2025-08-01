package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.ClientService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientServiceRepository extends JpaRepository<ClientService, Long> {
    List<ClientService> findAllByUser(long user);
}