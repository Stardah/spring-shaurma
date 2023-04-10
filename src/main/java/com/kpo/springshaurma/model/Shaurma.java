package com.kpo.springshaurma.model;

import lombok.Data;

import java.util.List;

@Data
public class Shaurma {

    private String name;

    private List<Ingredient> ingredients;
}
