package com.app.eRegistrar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // This will return home.html
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

}
