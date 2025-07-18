package fr.kainovaii.blogspring.controller.front;

import fr.kainovaii.blogspring.Component.RouteAccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice
{
    @Autowired
    private RouteAccessChecker webAccess;

    @ModelAttribute("username")
    public String addUsernameToModel(Authentication authentication) {
        if (authentication != null) {
            return authentication.getName();
        }
        return "Invité";
    }

    @ModelAttribute("webAccess")
    public RouteAccessChecker webAccess() {
        return webAccess;
    }
}