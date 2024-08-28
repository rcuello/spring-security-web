package com.tecno.web_sec.controllers.mvc;

import com.tecno.web_sec.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String showProducts(Model model) {
        
        List<Product> products = Arrays.asList(
            new Product("Computador", "Marca A"),
            new Product("Portátil", "Marca B"),
            new Product("Mouse", "Marca C"),
            new Product("Teclado", "Marca D"),
            new Product("Monitor", "Marca E")
        );

        model.addAttribute("products", products);
        return "products";
    }
}
