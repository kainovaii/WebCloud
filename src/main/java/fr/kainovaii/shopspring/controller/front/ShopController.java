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
@RequestMapping("/products")
public class ShopController
{
    @GetMapping("/server-vps-lxc")
    public String vps_lxc()
    {
        return "shop/vps-lxc";
    }
}