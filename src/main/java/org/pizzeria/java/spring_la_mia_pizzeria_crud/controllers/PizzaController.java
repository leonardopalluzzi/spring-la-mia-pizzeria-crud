package org.pizzeria.java.spring_la_mia_pizzeria_crud.controllers;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menu")
public class PizzaController {

    @Autowired
    PizzaRepository repo;

    @GetMapping
    public String index(Model model, @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit) {
        Pageable pagination = PageRequest.of(page, limit);

        Page<Pizza> pizzas = repo.findAll(pagination);
        model.addAttribute("pizzas", pizzas.toList());
        return "pizze/index";
    }

    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

}
