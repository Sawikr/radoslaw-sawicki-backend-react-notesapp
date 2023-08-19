package com.radoslawsawicki.backendreactnotesapp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/api")
    public String showHome() {
        return "home";
    }
}