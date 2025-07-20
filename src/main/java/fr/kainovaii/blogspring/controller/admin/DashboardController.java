package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class DashboardController
{

    private final PostService postService;

    public DashboardController(PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        model.addAttribute("postsCount", postService.countPosts());
        return "admin/dashboard";
    }
}