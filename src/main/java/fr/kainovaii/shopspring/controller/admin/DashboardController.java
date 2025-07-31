package fr.kainovaii.shopspring.controller.admin;

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

    public DashboardController(PostService postService, UserService userService, ProductService productService)
    {
        this.postService = postService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        model.addAttribute("postsCount", postService.count());
        model.addAttribute("usersCount", userService.count());
        model.addAttribute("productCount", productService.count());
        return "admin/dashboard";
    }
}