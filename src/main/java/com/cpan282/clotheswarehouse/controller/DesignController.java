package com.cpan282.clotheswarehouse.controller;

import java.util.EnumSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cpan282.clotheswarehouse.model.Cloth;
import com.cpan282.clotheswarehouse.model.Warehouse;
import com.cpan282.clotheswarehouse.model.Cloth.Brand;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui. Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/design")
@Slf4j // for logger
@SessionAttributes("wareHouse") // for increasing lifetime 
public class DesignController {

    @GetMapping
    public String design(){
        return "design";
    }

    @ModelAttribute   // for  passing clothes to form
    public void clothes(Model model){
        var clothes = EnumSet.allOf(Brand.class);      // this clothes is in html form and then we use for each loop to display.
        model.addAttribute("clothes",clothes);
        log.info("clothes converted to string: {}",clothes);
    }


    @ModelAttribute(name = "wareHouse") // We add cloth to the warehouse.
    public Warehouse wareHouse(){
        return new Warehouse();
    }


    @ModelAttribute   //We bind @ModelAttribute with html form and Cloth model is binded with html as a object.
    public Cloth cloth(){
        return Cloth
            .builder()
            .build();
    }

    @PostMapping
    public String processClothAddition(@Valid Cloth cloth, BindingResult result, @ModelAttribute Warehouse stock){
        if(result.hasErrors()){
            return "design";
        }
        stock.add(cloth);
        return "redirect:/design";
    }
}
