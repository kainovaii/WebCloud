package fr.kainovaii.shopspring.controller.admin;

import fr.kainovaii.shopspring.service.ClientServiceService;
import fr.kainovaii.shopspring.service.PostService;
import fr.kainovaii.shopspring.service.ProductService;
import fr.kainovaii.shopspring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
@Controller
@RequestMapping("/admin")
public class DashboardController
{
    private final PostService postService;
    private final UserService userService;
    private final ProductService productService;
    private final ClientServiceService clientServiceService;

    public DashboardController(PostService postService, UserService userService, ProductService productService, ClientServiceService clientServiceService)
    {
        this.postService = postService;
        this.userService = userService;
        this.productService = productService;
        this.clientServiceService = clientServiceService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        model.addAttribute("postsCount", postService.count());
        model.addAttribute("usersCount", userService.count());
        model.addAttribute("productCount", productService.count());
        model.addAttribute("clientServiceCount", clientServiceService.count());
        return "admin/dashboard";
    }
}