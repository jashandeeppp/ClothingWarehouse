package com.cpan282.clotheswarehouse.model.dto;

import com.cpan282.clotheswarehouse.model.Cloth.Brand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClothSearchByYearDto {
    private Brand brand;
    private String year;
}
