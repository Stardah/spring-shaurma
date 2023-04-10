package com.kpo.springshaurma.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kpo.springshaurma.model.Ingredient;
import com.kpo.springshaurma.model.Order;
import com.kpo.springshaurma.model.Shaurma;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class CreateShaurmaController {

    @ModelAttribute
    public void addDataToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CHEESE", "Сырный", Ingredient.Type.SAUCE),
                new Ingredient("SRSW", "Кисло-сладкий", Ingredient.Type.SAUCE),
                new Ingredient("MAZIK", "Майонез", Ingredient.Type.SAUCE),
                new Ingredient("KTCHUNEZ", "100 Островов", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "shaurma")
    public Shaurma shaurma() {
        return new Shaurma();
    }

    @GetMapping
    public String getForm() {
        return "shaurma";
    }

    @PostMapping
    public String createShaurma(Shaurma shaurma, @ModelAttribute Order order) {
        order.addShaurma(shaurma);

        if (log.isInfoEnabled()) {
            log.info("Added shaurma {}", shaurma);
        }

        return "redirect:/orders/current";
    }

    private Object filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.type().equals(type))
                .collect(Collectors.toList());
    }

}
