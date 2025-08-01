package fr.kainovaii.shopspring.controller.admin;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.service.ClientServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
@Controller("clientServiceController")
@RequestMapping("/admin/services")
public class ServiceController
{
    private final ClientServiceService clientServiceService;

    public ServiceController(ClientServiceService clientServiceService)
    {
        this.clientServiceService = clientServiceService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        List<ClientService> clientServices = clientServiceService.findAll();
        model.addAttribute("clientServices", clientServices);

        return "admin/services/list";
    }
}