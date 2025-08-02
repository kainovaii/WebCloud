package fr.kainovaii.shopspring.controller;

import fr.kainovaii.shopspring.component.RouteAccessChecker;
import fr.kainovaii.shopspring.model.User;
import fr.kainovaii.shopspring.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice
{
    @Autowired
    private final RouteAccessChecker webAccess;
    private final CurrentUserService currentUserService;

    @Autowired
    public GlobalControllerAdvice(RouteAccessChecker webAccess, CurrentUserService currentUserService)
    {
        this.webAccess = webAccess;
        this.currentUserService = currentUserService;
    }

    @ModelAttribute("siteName")
    public String siteName() {
        return "WebCloud";
    }

    @ModelAttribute("currentUser")
    public User currentUser()
    {
        try {
            return currentUserService.getCurrentUser();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @ModelAttribute("webAccess")
    public RouteAccessChecker webAccess() {
        return webAccess;
    }
}