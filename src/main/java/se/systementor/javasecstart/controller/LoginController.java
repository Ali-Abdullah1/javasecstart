package se.systementor.javasecstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("activeFunction", "login");
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/admin/dogs";
        } else {
            model.addAttribute("error", "Fel användarnamn eller lösenord.");
            return "login";
        }
    }
}
