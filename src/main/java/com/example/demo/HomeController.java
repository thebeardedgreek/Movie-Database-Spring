package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String listMovies(Model model){
        model.addAttribute("movies", movieRepository.findAll());
        return "movielist";
    }

    @GetMapping("/add")
    public String loadFormPage(Model model){
        model.addAttribute("movie", new Movie());
        return "movieform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Movie movie, BindingResult result){
        if(result.hasErrors()){
            return "movieform";
        }
        movieRepository.save(movie);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String displayMovie(@PathVariable("id") long id, Model model){
        model.addAttribute("movie", movieRepository.findById(id).get());
        return "displaymovie";
    }

    @RequestMapping("/update/{id}")
    public String updateMovie(@PathVariable("id") long id, Model model){
        model.addAttribute("movie", movieRepository.findById(id).get());
        return "movieform";
    }

    @RequestMapping("/delete/{id}")
    public String delMovie(@PathVariable("id") long id){
        movieRepository.deleteById(id);
        return "redirect:/";
    }

}
