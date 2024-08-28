package com.tecno.web_sec.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public RedirectView logout() {
        // Redirige al usuario a la página de inicio de sesión con un parámetro de cierre de sesión
        return new RedirectView("/login?logout");
    }
}
