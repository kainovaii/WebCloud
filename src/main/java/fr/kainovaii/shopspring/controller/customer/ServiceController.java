package fr.kainovaii.shopspring.controller.customer;

import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ServiceController
{
    private final UserService userService;

    public ServiceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/services")
    public String services()
    {

        return "customer/services";
    }
}