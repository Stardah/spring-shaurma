package ru.kpo.spring.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private List<Shaurma> shaurmaList = new ArrayList<>();

    public void addShaurma(Shaurma shaurma) {
        this.shaurmaList.add(shaurma);
    }
}
