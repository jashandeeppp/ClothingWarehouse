package com.cpan282.clotheswarehouse.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Warehouse {
    private List<Cloth> clothes;

    public Warehouse(){
        this.clothes = new ArrayList<>();
    }

    public void add(Cloth cloth){
        this.clothes.add(cloth);
    }
}
