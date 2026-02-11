package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.lessons.java.spring_la_mia_pizzeria_crud.classes.Pizza;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        List<Pizza> pizza;

        if (search.isEmpty()) {
            pizza = repository.findAll();
        } else {
            pizza = repository.findByNameContainingIgnoreCase(search);
        }

        model.addAttribute("pizza", pizza);
        model.addAttribute("search", search);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/detail-pizza";
    }

}
