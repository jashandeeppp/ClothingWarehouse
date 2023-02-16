package com.cpan282.clotheswarehouse.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

// Created model for shop.
public class Cloth {
    private Long id;
    @NotBlank
    private String name;
    @Min(2021)
    private int yearOfCreation;
    @Min(1000)
    private BigDecimal price;
    private Brand brandFrom;

    public enum Brand{
        
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior");
        private String title;

        private Brand(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }
    }
}
