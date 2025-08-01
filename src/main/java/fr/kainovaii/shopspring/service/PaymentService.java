package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
    public boolean verify(Order order) {
        return order.getId() % 2 == 0;
    }
}

