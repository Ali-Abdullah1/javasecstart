package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;

import java.util.Optional;

@Controller
public class AdminDogController {
    @Autowired
    private DogService dogService;

    @GetMapping(path="/admin/dogs")
    String list(Model model){
        model.addAttribute("activeFunction", "home");
        model.addAttribute("dogs", dogService.getPublicDogs());
        return "admin/dogs/list";
    }

    @GetMapping(path="/admin/dogs/edit/{id}")
    public String editdogs(@PathVariable("id") int id, Model model) {
        Optional<Dog> dog = dogService.getDogById(id);
        if (dog.isPresent()) {
            model.addAttribute("dog", dog.get());
            return "admin/dogs/edit";
        } else {
            return "redirect:/admin/dogs";
        }
    }

    @PostMapping(path="/admin/dogs/update/{id}")
    public String changeDog(@PathVariable("id") int id, @ModelAttribute Dog dog) {
        Optional<Dog> existingDog = dogService.getDogById(id);
        if (existingDog.isPresent()) {
            Dog updatedDog = existingDog.get();
            updatedDog.setName(dog.getName());
            updatedDog.setBreed(dog.getBreed());
            updatedDog.setAge(dog.getAge());
            updatedDog.setSize(dog.getSize());
            updatedDog.setPrice(dog.getPrice());
            dogService.saveDog(updatedDog);
            return "redirect:/admin/dogs";
        } else {
            return "redirect:/admin/dogs";
        }
    }
}
