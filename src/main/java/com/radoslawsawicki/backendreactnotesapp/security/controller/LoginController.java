package com.radoslawsawicki.backendreactnotesapp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPages")
    public String showMyLoginPages() {
        return "fancy-login";
    }
}