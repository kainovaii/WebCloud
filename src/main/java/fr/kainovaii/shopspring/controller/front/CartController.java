package fr.kainovaii.shopspring.controller.front;

import fr.kainovaii.shopspring.model.CartItem;
import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController
{
    private static final String CART_SESSION_KEY = "cart";

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String viewCart(Model model,  HttpSession session)
    {
        Collection<CartItem> cart = getCart(session).values();
        double total = getCart(session).values().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/add/{productId}")
    public RedirectView addProduct(@PathVariable Long productId, @RequestParam int quantity, HttpSession session, RedirectAttributes redirectAttributes)
    {
        Optional<Product> optionalProduct = productService.getProductById(productId);
        if (optionalProduct.isEmpty()) {
            redirectAttributes.addFlashAttribute("successMessage", "Product not found");
            return new RedirectView("/cart");
        }
        Product product = optionalProduct.get();
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(productId);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            cart.put(productId, new CartItem(product, quantity));
        }
        session.setAttribute(CART_SESSION_KEY, cart);
        return new RedirectView("/cart");
    }

    @GetMapping("/total")
    public double getTotal(HttpSession session) {
        return getCart(session).values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    @DeleteMapping("/remove/{productId}")
    public String removeItem(@PathVariable Long productId, HttpSession session) {
        getCart(session).remove(productId);
        return "Product removed from cart.";
    }

    @DeleteMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
        return "Cart cleared.";
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartItem> getCart(HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
}