package com.act2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.act2.model.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
    public String saveUser(@Valid @ModelAttribute("user") User user, 
                           BindingResult result, 
                           HttpSession session) {
        
        if (result.hasErrors()) {
            return "form";
        }

        user.setNama(HtmlUtils.htmlEscape(user.getNama().trim()));
        user.setId(HtmlUtils.htmlEscape(user.getId().trim()));
        user.setNim(user.getNim().trim());

        List<User> userList = (List<User>) session.getAttribute("userList");
        if (userList == null) userList = new ArrayList<>();

        userList.add(user);
        session.setAttribute("userList", userList);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hapus semua data session
        return "redirect:/";
    }

}
