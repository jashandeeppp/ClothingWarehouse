package com.cpan282.clotheswarehouse.model.dto;

import java.util.List;

import com.cpan282.clotheswarehouse.model.Cloth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionCentreDto {
    private String name;
    private int itemsAvailable;
    private double latitude;
    private double longitude;
    private List<ItemsDto> items;
}