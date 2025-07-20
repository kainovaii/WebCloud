package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ADMIN')")
@Controller("adminUserController")
@RequestMapping("/admin/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "admin/users/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id)
    {
        return userService.findById(id)
        .map(user -> {
            model.addAttribute("user", user);
            return "admin/users/edit";
        })
        .orElse("error/404");
    }
}