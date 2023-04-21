package com.cpan282.clotheswarehouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ItemsDto {
    private String name;
    private int yearOfCreation;
    private double price;
    private int quantity;
    private String brandFrom;

}
