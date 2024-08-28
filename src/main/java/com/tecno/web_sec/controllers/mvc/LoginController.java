package com.tecno.web_sec.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public ModelAndView showLoginForm(
                                    @RequestParam(value = "error", required = false) String error,
                                    @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView("login");

        if (error != null) {
            modelAndView.addObject("errorMessage", error);            
        }

        if (logout != null) {
            modelAndView.addObject("infoMessage", "Sesión cerrada exitosamente.");
        }
        
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView  postMethodName(@RequestParam String username, @RequestParam String password) {

        logger.info("Usuario => {}", username);

        if (username.equals("error")) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Usuario o contraseña incorrectos.");
            return modelAndView;
        }
        logger.info("Redirect to home");

        // Redirige a la página de inicio si el login es exitoso
        return new ModelAndView("redirect:/home"); 
        
    }
    
}
