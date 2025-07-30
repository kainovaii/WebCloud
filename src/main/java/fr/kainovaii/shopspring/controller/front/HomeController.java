package fr.kainovaii.shopspring.controller.front;

import fr.kainovaii.shopspring.model.Post;
import fr.kainovaii.shopspring.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class HomeController
{
    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication)
    {
        List<Post> posts = postService.findAll().stream()
            .filter(post -> post.getStatus() == 1)
            .collect(Collectors.toList());

        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/nos-offres")
    public String offers()
    {
        return "offers";
    }
}