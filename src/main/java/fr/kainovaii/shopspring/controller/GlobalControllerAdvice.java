package fr.kainovaii.shopspring.controller;

import fr.kainovaii.shopspring.component.RouteAccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice
{
    @Autowired
    private RouteAccessChecker webAccess;

    @ModelAttribute("siteName")
    public String siteName() {
        return "WebCloud";
    }

    @ModelAttribute("webAccess")
    public RouteAccessChecker webAccess() {
        return webAccess;
    }
}