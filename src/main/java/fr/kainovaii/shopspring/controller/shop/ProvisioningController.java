package fr.kainovaii.shopspring.controller.shop;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.Order;
import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.*;
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
    private final ProductService productService;
    private final CurrentUserService currentUserService;

    public ProvisioningController(OrderService orderService, ClientServiceService clientServiceService, ProductService productService1, CurrentUserService currentUserService)
    {
        this.orderService = orderService;
        this.clientServiceService = clientServiceService;
        this.productService = productService1;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/start/{orderId}")
    public ResponseEntity<?> startProvisioning(@PathVariable Long orderId)
    {
        Optional<Order> optionalOrder = orderService.findOrderById(orderId);

        User user = currentUserService.getCurrentUser();
        user.setId(user.getId());

        if (optionalOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commande introuvable.");
        }

        Order order = optionalOrder.get();
        ClientService clientService = new ClientService();

        try {
            Optional<Product> product = productService.findById(1L);
            clientService.setUser(user);
            clientService.setServiceName("VPS Ubuntu");
            clientService.setPrice(9.99);
            clientService.setProductId(product.get().getId());
            clientService.setStatus(2L);
            clientService.setType(product.get().getType());
            clientService.setDuration(30);

            ClientService savedService = clientServiceService.create(clientService);

            return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", "/client/services")
                .build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error provisioning : " + e.getMessage());
        }
    }
}

