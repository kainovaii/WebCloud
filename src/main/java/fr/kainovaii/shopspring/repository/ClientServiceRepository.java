package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientServiceRepository extends JpaRepository<ClientService, Long>
{
    @Query("SELECT cs FROM ClientService cs WHERE cs.user.id = :userId")
    List<ClientService> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(cs) FROM ClientService cs WHERE cs.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);
}