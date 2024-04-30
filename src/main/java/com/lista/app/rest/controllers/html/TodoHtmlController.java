package com.lista.app.rest.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoHtmlController {
    @GetMapping("/todo")
    public String todo(){
        return "todo";
    }
}
