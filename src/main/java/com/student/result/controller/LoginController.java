package com.student.result.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        // Hardcoded admin credentials
        if (username.equals("admin") && password.equals("admin123")) {

            session.setAttribute("admin", username);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
