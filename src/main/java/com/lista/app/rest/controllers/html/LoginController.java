package com.lista.app.rest.controllers.html;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/acceder")
    public String login(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            // Usuario autenticado, redirigir a otra página
            return "redirect:/";
        }

        return "acceder";
    }

}
