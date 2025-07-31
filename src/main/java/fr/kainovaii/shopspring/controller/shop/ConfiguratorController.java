package fr.kainovaii.shopspring.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kainovaii.shopspring.model.CartItem;
import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/configurator")
public class ConfiguratorController
{
    private static final String CONFIGURATOR_SESSION_KEY = "configurator";

    private final ProductRepository productRepository;

    public ConfiguratorController(ProductRepository productRepository) { this.productRepository = productRepository; }

    @GetMapping("")
    public String viewConfigurator(Model model, HttpSession session) {
        Product configurator = getSession(session);
        model.addAttribute("product", configurator);

        ObjectMapper objectMapper = new ObjectMapper();
        String metadataJson = "{}";
        try {
            metadataJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(configurator.getMetadata());
        } catch (Exception e) {
            metadataJson = "{\"error\": \"Impossible de parser le metadata\"}";
        }

        model.addAttribute("metadataJson", metadataJson);

        return "shop/configurator";
    }

    @GetMapping("/add/{productId}")
    public String addToConfigurator(@PathVariable Long productId, HttpSession session)
    {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            session.setAttribute(CONFIGURATOR_SESSION_KEY, product);
        }
        return "redirect:/configurator";
    }

    private Product getSession(HttpSession session)
    {
        Product configurator = (Product) session.getAttribute(CONFIGURATOR_SESSION_KEY);
        if (configurator == null) {
            configurator = new Product();
            session.setAttribute(CONFIGURATOR_SESSION_KEY, configurator);
        }
        return configurator;
    }
}