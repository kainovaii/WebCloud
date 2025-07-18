package fr.kainovaii.blogspring.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminPostController")
@RequestMapping("/admin/posts")
public class PostController
{
    @GetMapping("")
    public String home(Model model, Authentication authentication) {
        return "admin/posts";
    }
}