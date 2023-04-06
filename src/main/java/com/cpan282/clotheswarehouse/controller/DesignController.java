package com.cpan282.clotheswarehouse.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cpan282.clotheswarehouse.model.Cloth;
import com.cpan282.clotheswarehouse.model.User;
import com.cpan282.clotheswarehouse.model.Cloth.Brand;
import com.cpan282.clotheswarehouse.repository.ClothRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui. Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/design")
@Slf4j // for logger
@SessionAttributes("warehouse") // for increasing lifetime 
public class DesignController {

    @Autowired      // this annotation injects bean to other bean.
    private ClothRepository clothRepository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') || hasRole('ROLE_ADMIN')")  
    public String design(){
        return "design";
    }

    @ModelAttribute   // for  passing clothes to form
    public void clothes(Model model){
        var clothes = EnumSet.allOf(Brand.class);      // this clothes is used in html form and then we use for each loop to display the brand.
        model.addAttribute("clothes",clothes);
        log.info("clothes converted to string: {}",clothes);
    }

    @ModelAttribute   //We bind @ModelAttribute with html form and Cloth model is binded with html as a object through this method.
    public Cloth cloth(){
        return Cloth
            .builder()
            .build();
    }

    @PostMapping
    public String processClothAddition(@Valid Cloth cloth, BindingResult result){
        if(result.hasErrors()){
            return "design";
        }
        clothRepository.save(cloth);
        return "redirect:/clothlist";
    }

}
