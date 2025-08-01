package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.Order;
import fr.kainovaii.shopspring.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService
{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public boolean isPaid(Order order) {
        return "PAID".equalsIgnoreCase(order.getStatus());
    }

    public void markAsPaid(Order order) {
        order.setStatus("PAID");
        orderRepository.save(order);
    }

    public void markAsProvisioned(Order order) {
        order.setStatus("PROVISIONED");
        orderRepository.save(order);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

