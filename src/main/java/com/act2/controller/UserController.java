package com.act2.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

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
        HttpSession session,
            Model model) {
        if (username.equals(USERNAME) && password.equals(PASSWORD))
            {
                session.setAttribute("isLoggedIn", true);
                return "home";
            } else {
                model.addAttribute("error","Username atau Password kosong");
                return "login";
            }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("isLoggedIn") == null ) return "home";

        List<User> userList = (List<User>) session.getAttribute("userList");
        if (userList == null) userList = new ArrayList<>();

        model.addAttribute("users", userList);
        return "home";

    }

    @GetMapping("/create")
    public String showForm(HttpSession session) {
        if (session.getAttribute("isLoggedIn") == null) return "redirect:/";
        return "form";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String id, 
                           @RequestParam String nama, 
                           @RequestParam String nim, 
                           @RequestParam String gender, 
                           HttpSession session) {
        
        List<User> userList = (List<User>) session.getAttribute("userList");
        if (userList == null) userList = new ArrayList<>();

        userList.add(new User(id, nama, nim, gender));
        session.setAttribute("userList", userList);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hapus semua data session
        return "redirect:/";
    }

}
