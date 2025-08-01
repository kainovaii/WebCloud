package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService
{
    private final UserService userService;

    public CurrentUserService(UserService userService)
    {
        this.userService = userService;
    }

    public Long getCurrentUserId()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public User getCurrentUser()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
