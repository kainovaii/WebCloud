package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.service.PostService;
import fr.kainovaii.blogspring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class SettingsController
{
    @GetMapping("/settings")
    public String home(Model model)
    {
        return "admin/settings";
    }
}