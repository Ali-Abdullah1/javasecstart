package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.services.AccountService;

import java.text.AttributedString;

@Controller
public class PublicAccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping(path="/CreateAccount")
    String list(Model model) {
        model.addAttribute("activeFunction", "CreateAccount");
        return "CreateAccount";
    }

    @PostMapping("/CreateAccount")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model){
        try {
            accountService.register(username, password, email);
            return "home";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "CreateAccount";
        }
    }
}
