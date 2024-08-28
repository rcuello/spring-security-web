package com.tecno.web_sec.controllers.mvc;

import com.tecno.web_sec.models.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SellerController {

    @GetMapping("/sellers")
    public String showSellers(Model model) {
        List<Seller> sellers = Arrays.asList(
            new Seller("Juan", "Pérez"),
            new Seller("Ana", "García"),
            new Seller("Luis", "Martínez"),
            new Seller("María", "López"),
            new Seller("Pedro", "Sánchez")
        );

        // Descomentar para probar el manejo de excepciones
        /*sellers = new ArrayList<Seller>();

        if (sellers.isEmpty()) {
            throw new IllegalStateException("La lista de vendedores está vacía");
        }*/

        model.addAttribute("sellers", sellers);
        return "sellers";
    }
}
