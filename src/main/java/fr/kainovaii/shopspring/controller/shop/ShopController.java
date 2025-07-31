package fr.kainovaii.shopspring.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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