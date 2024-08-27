package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogServiceImpl;

import java.util.List;

@Controller
//@RequestMapping("/admin/dogs")
public class AdminDogController {
    @Autowired
    private DogServiceImpl dogService;

/*
    @GetMapping(path="/admin/dogs")
    String list(Model model){
        model.addAttribute("activeFunction", "home");
//        setupVersion(model);

        model.addAttribute("dogs", dogService.getPublicDogs());
        return "admin/dogs/list";
    }
    */

    @GetMapping(path="/admin/dogs")
    String list(Model model, @RequestParam(defaultValue = "1") int pageNo,
                 @RequestParam(defaultValue = "10") int pageSize,
                 @RequestParam(defaultValue = "name") String sortCol,
                 @RequestParam(defaultValue = "asc") String sortOrder,
                 @RequestParam(defaultValue = "") String q) {

        q = q.trim();

        model.addAttribute("q", q);
        model.addAttribute("sortCol", sortCol);
        model.addAttribute("sortOrder", sortOrder);

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if (!q.isEmpty()) {
            List<Dog> dogList = dogService.findAllByDogNameContains(q, sort);
            model.addAttribute("dogs", dogList);
            model.addAttribute("totalPages", 1);
            model.addAttribute("pageNo", 1);
        } else {
            List<Dog> dogList = dogService.findAllDogs(sort);
            model.addAttribute("dogs", dogList);
            model.addAttribute("name", "Dog name");

            model.addAttribute("totalPages", 1);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("dogs", dogList);
        }
        return "admin/dogs/list";
    }
}