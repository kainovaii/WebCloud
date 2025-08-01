package fr.kainovaii.shopspring.controller.customer;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.service.ClientServiceService;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.kainovaii.shopspring.model.User;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController
{
    private final UserService userService;
    private final ClientServiceService clientServiceService;

    public ClientController(UserService userService, ClientServiceService clientServiceService) {
        this.userService = userService;
        this.clientServiceService = clientServiceService;
    }

    @GetMapping
    public String dashboard() {
        return "customer/dashboard";
    }

    @GetMapping("/services")
    public String services(Model model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userService.findByUsername(username);
        long userId = optionalUser.get().getId();

        List<ClientService> services = clientServiceService.getServicesByUser(userId);
        model.addAttribute("services", services);

        return "customer/services";
    }
}
