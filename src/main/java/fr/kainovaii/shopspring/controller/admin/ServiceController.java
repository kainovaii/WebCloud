package fr.kainovaii.shopspring.controller.admin;

import com.github.slugify.Slugify;
import fr.kainovaii.shopspring.dto.PostWithAuthor;
import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.Post;
import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.ClientServiceService;
import fr.kainovaii.shopspring.service.PostService;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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