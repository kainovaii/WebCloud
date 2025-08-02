package fr.kainovaii.shopspring.controller.shop;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.Order;
import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/provision")
public class ProvisioningController
{
    private final OrderService orderService;
    private final ClientServiceService clientServiceService;
    private final CurrentUserService currentUserService;

    public ProvisioningController(OrderService orderService, ClientServiceService clientServiceService, CurrentUserService currentUserService)
    {
        this.orderService = orderService;
        this.clientServiceService = clientServiceService;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/start/{orderId}")
    public ResponseEntity<?> startProvisioning(@PathVariable Long orderId, HttpSession session)
    {
        Optional<Order> optionalOrder = orderService.findOrderById(orderId);

        User user = currentUserService.getCurrentUser();
        user.setId(user.getId());

        if (optionalOrder.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande introuvable.");
        }

        Order order = optionalOrder.get();
        ClientService clientService = new ClientService();

        try {
            Product product = ConfiguratorController.getSession(session);
            clientService.setUser(user);
            clientService.setServiceName(product.getName());
            clientService.setPrice(product.getPrice());
            clientService.setProductId(product.getId());
            clientService.setStatus(2L);
            clientService.setType(product.getType());
            clientService.setDuration(30);

            ClientService savedService = clientServiceService.create(clientService);

            return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", "/client/orders")
                .build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error provisioning : " + e.getMessage());
        }
    }
}

