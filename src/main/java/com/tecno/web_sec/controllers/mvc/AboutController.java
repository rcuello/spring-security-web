package com.tecno.web_sec.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/public/about")
    public String showAboutPage() {
        return "public/about"; // Retorna la vista "about.html"
    }
}
