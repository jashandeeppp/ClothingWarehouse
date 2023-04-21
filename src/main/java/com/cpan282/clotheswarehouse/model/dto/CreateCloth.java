package com.cpan282.clotheswarehouse.model.dto;

import com.cpan282.clotheswarehouse.model.Cloth;
import com.cpan282.clotheswarehouse.model.Cloth.Brand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCloth {

    @NotBlank
    private String name;
    
    @Min(2021)
    private int yearOfCreation;

    @Min(1000)
    private double price;

    @Min(1)
    private int quantity;

    @NotNull
    private Brand brandFrom;

    public Cloth toCloth(){
        return Cloth.builder()
                .name(this.getName())
                .yearOfCreation(this.getYearOfCreation())
                .price(this.getPrice())
                .brandFrom(this.getBrandFrom())
                .build();
    }

}
