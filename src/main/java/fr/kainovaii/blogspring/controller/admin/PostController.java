package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminPostController")
@RequestMapping("/admin/posts")
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "admin/posts/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        return postService.findById(id)
        .map(post -> {
            model.addAttribute("post", post);
            return "admin/posts/edit";
        })
        .orElse("error/404");
    }
}