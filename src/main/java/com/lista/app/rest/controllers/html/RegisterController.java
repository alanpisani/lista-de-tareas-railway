package com.lista.app.rest.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }
}
