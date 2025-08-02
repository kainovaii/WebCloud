package fr.kainovaii.shopspring.controller.customer;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.Order;
import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.ClientServiceService;
import fr.kainovaii.shopspring.service.CurrentUserService;
import fr.kainovaii.shopspring.service.OrderService;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController
{
    private final ClientServiceService clientServiceService;
    private final CurrentUserService currentUserService;
    private final OrderService orderService;

    public ClientController(UserService userService, ClientServiceService clientServiceService, CurrentUserService currentUserService, OrderService orderService)
    {
        this.clientServiceService = clientServiceService;
        this.currentUserService = currentUserService;
        this.orderService = orderService;
    }

    @GetMapping
    public String dashboard(Model model)
    {
        long serviceCount = clientServiceService.countServicesByUser(currentUserService.getCurrentUserId());
        long orderCount = orderService.countOrderByUser(currentUserService.getCurrentUserId());
        model.addAttribute("serviceCount", serviceCount);
        model.addAttribute("orderCount", orderCount);

        return "customer/dashboard";
    }

    @GetMapping("/services")
    public String services(Model model)
    {
        List<ClientService> services = clientServiceService.getServicesByUser(currentUserService.getCurrentUserId());
        model.addAttribute("services", services);

        return "customer/services";
    }

    @GetMapping("/orders")
    public String orders_list(Model model)
    {
        List<Order> orders = orderService.findByUser(currentUserService.getCurrentUserId());
        model.addAttribute("orders", orders);

        return "customer/orders/list";
    }

    @GetMapping("/orders/{id}")
    public String orders_show(Model model, @PathVariable long id)
    {
        return orderService.findOrderById(id)
            .map(order -> {
                model.addAttribute("order", order);
                return "customer/orders/show";
            })
            .orElse("error/404");
    }
}
