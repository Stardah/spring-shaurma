package com.kpo.springshaurma.service;

import com.kpo.springshaurma.model.Ingredient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceSampleImpl implements ServiceSample {

    public void modifyModel(Model model) {
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

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.type().equals(type))
                .toList();
    }
}
