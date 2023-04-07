package ru.kpo.spring.model;

public record Ingredient(String id, String name, ru.kpo.spring.model.Ingredient.Type type) {

    public enum Type {
        SAUCE
    }
}
