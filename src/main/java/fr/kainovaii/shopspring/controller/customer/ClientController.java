package fr.kainovaii.shopspring.controller.customer;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.service.ClientServiceService;
import fr.kainovaii.shopspring.service.CurrentUserService;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController
{
    private final ClientServiceService clientServiceService;
    private final CurrentUserService currentUserService;

    public ClientController(UserService userService, ClientServiceService clientServiceService, CurrentUserService currentUserService) {
        this.clientServiceService = clientServiceService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public String dashboard(Model model)
    {
        long serviceCount = clientServiceService.countServicesByUser(currentUserService.getCurrentUserId());
        model.addAttribute("serviceCount", serviceCount);

        return "customer/dashboard";
    }

    @GetMapping("/services")
    public String services(Model model)
    {
        List<ClientService> services = clientServiceService.getServicesByUser(currentUserService.getCurrentUserId());
        model.addAttribute("services", services);

        return "customer/services";
    }
}
