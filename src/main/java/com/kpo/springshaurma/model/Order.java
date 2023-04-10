package com.kpo.springshaurma.model;

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
