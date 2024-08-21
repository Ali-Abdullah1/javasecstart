package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.systementor.javasecstart.services.AccountService;

@Controller
public class PublicAccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path="/CreateAccount")
    String list(Model model) {
        model.addAttribute("activeFunction", "CreateAccount");
        return "CreateAccount";
    }
}
