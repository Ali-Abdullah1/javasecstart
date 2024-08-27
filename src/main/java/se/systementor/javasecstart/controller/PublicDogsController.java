package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.systementor.javasecstart.services.DogServiceImpl;

@Controller
public class PublicDogsController {
    @Autowired
    private DogServiceImpl dogService;
    @GetMapping(path="/dogs")
    String list(Model model){
        model.addAttribute("activeFunction", "publicdogs");
//        setupVersion(model);

        model.addAttribute("dogs", dogService.getPublicDogs());
        return "dogs";
    }

}