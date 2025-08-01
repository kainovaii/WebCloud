package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.ClientService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientServiceRepository extends JpaRepository<ClientService, Long> {
}