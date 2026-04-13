package com.act2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20220140102";

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
            Model model) {
        if (username.equals(USERNAME) && password.equals(PASSWORD))
            {
                return "home";
            } else {
                model.addAttribute("error","Username atau Password kosong");
                return "login";
            }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
