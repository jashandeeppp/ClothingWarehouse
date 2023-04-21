package com.cpan282.clotheswarehouse.model.dto;

import com.cpan282.clotheswarehouse.model.Cloth.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSearchByNameDto {
    private String name;
    private String brand;
}
