package fr.kainovaii.shopspring.controller.admin;

import fr.kainovaii.shopspring.model.Product;
import fr.kainovaii.shopspring.service.ProductService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller("adminProductController")
@RequestMapping("/admin/products")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String home(Model model)
    {
        List<Product> products = productService.findAll();

        model.addAttribute("products", products);
        return "admin/products/list";
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes redirectAttributes)
    {
        try {
            productService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Product with ID " + id + " not found.");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Success");
        return new RedirectView("/admin/products");
    }
}