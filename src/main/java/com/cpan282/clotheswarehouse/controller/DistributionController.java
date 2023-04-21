package com.cpan282.clotheswarehouse.controller;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cpan282.clotheswarehouse.model.Cloth.Brand;
import com.cpan282.clotheswarehouse.model.dto.DistributionCentreDto;
import com.cpan282.clotheswarehouse.model.dto.ItemSearchByNameDto;

import lombok.extern.slf4j.Slf4j;

// THIS CONTROLLER USED FOR FETCHING DATA FROM THE DISTRIBUTION CENTRES APP.

@Slf4j
@Controller
@RequestMapping("distribution")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:8081")     // ensure that URL is trusted and data can be consumed from here.
public class DistributionController {
    
    private RestTemplate restTemplate;
    final double LATITUDE = 43.6532;
    final double LONGITUDE = 79.3470;

    public DistributionController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String showDistributions(Model model){
        model.addAttribute("container", true);
        return "distribution";
    }

    @ModelAttribute("centres")
    public List<DistributionCentreDto> getCentres(){
        var centres = restTemplate.getForObject("http://localhost:8081/api/distribution", DistributionCentreDto[].class);
        return Arrays.asList(centres);
    }

    @ModelAttribute   // for  passing clothes to form
    public void clothesBrand(Model model){
        var clothes = EnumSet.allOf(Brand.class); // this clothes is used in html form and then we use for each loop to display the brand.
        model.addAttribute("clothesBrand",clothes);
    }

    @ModelAttribute
    public void itemSearchbyName(Model model){
        model.addAttribute("itemSearchbyName", new ItemSearchByNameDto());
    }

    @PostMapping()  // This method for searching the item and that returns all the centres with that item and mention the closest.
    public String searchedItems(@ModelAttribute ItemSearchByNameDto itemSearchByNameDto, Model model) {
        String name = itemSearchByNameDto.getName();
        String brand = itemSearchByNameDto.getBrand();
        String url = "http://localhost:8081/api/distribution/centres/"+name+"/"+brand;
        var centres = restTemplate.getForObject(url, DistributionCentreDto[].class);

        if(centres.length == 0){
            model.addAttribute("message",true);
            model.addAttribute("container", false);
            return "distribution";
        } else {
        double shortest = Double.POSITIVE_INFINITY;
        String nearStore = "";
        for(DistributionCentreDto centre : centres){
            double lati = centre.getLatitude();
            double longi = centre.getLongitude();
            double distance = Math.sqrt(Math.pow((LATITUDE - lati), 2) + Math.pow((LONGITUDE - longi), 2));
            
            if(distance < shortest){
                shortest = distance;
                nearStore = centre.getName();
            }
            log.info("Centre name: "+centre.getName());
        }

        model.addAttribute("container", true);
        model.addAttribute("nearStore", nearStore);
        model.addAttribute("centres", centres);
        return "distribution";
    }
    }


}
