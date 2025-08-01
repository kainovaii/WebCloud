package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    List<Order> findByUserId(Long userId);
}
