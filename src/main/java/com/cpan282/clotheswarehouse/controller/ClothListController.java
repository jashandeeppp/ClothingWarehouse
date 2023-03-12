package com.cpan282.clotheswarehouse.controller;

import java.util.EnumSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan282.clotheswarehouse.model.Cloth.Brand;
import com.cpan282.clotheswarehouse.model.dto.ClothSearchByYearDto;
import com.cpan282.clotheswarehouse.repository.ClothRepository;


@Controller
@RequestMapping("/clothlist")
public class ClothListController {
    
    private ClothRepository clothRepository;        // clothrepository used for CRUD commands such as create, update, delete .

    public ClothListController(ClothRepository clothRepository){        // implemted the ClothRepostory interface.
        this.clothRepository = clothRepository;
    }

    @GetMapping
    public String showClothes(Model model){
        return "clothlist";
    }

    @ModelAttribute
    public void clothes(Model model){
        model.addAttribute("clothes",clothRepository.findAll());
    }

    @ModelAttribute   // for  passing clothes to form
    public void clothesBrand(Model model){
        var clothes = EnumSet.allOf(Brand.class);      // this clothes is used in html form and then we use for each loop to display the brand.
        model.addAttribute("clothesBrand",clothes);
    }

    @ModelAttribute
    public void clothesByYearDto(Model model){
        model.addAttribute("clothesByYearDto", new ClothSearchByYearDto());
    }
    /*
     * WE use clothesByYearDto model attribute from clothlist.html which is attribute name for the empty container.
     * When we fill the form we provide all the data and then we search by name and date then all the selected data gets filled up in this container.
     * Which means that we are filling this container clothesByYearDto which is the attribute name for the object of ClothSearchByYearDto.
     * So, in simple words when we fill the form clothesByYearDto attribute name is used it and it instantiated the object of the ClothSearchByYearDto which contains all the fields 
     * and which is used to populate the fields with the values which we provided from the form and which is going to be used in below method.
     */

    @PostMapping
    public String searchClothesByYear(@ModelAttribute ClothSearchByYearDto clothesByYearDto,
    Model model){
        /*
         * here we are filtering out the clothes with clothRepository which has CRUD commands in it and we provide our own query to filter out
         * We are able to use getBrand() or getYear() because we have provided @Data which helps in generating getters for all fields for us in the ClothSearchByYearDto 
         */
        model.addAttribute("clothes", clothRepository.findByBrandFromAndYearOfCreation(
          clothesByYearDto.getBrand(),
          clothesByYearDto.getYear()));
        return "clothlist";
    }
    /*
     * When http request is made, the object clothesByYearDto automatically populates the value from the framework because it is annotated with @ModelAttribute.
     * then the method calls findByBrandFromAndYearOfCreation() method on the clothRepository and we pass the brand and year from clothesByYearDto which was automatically populated with the values earlier.
     * findByBrandFromAndYearOfCreation() method searches the database for cloth table that match the values and it return the list of cloth objects.
     * Then the filtered out clothes  we put it into the attribute name clothes which we have already used it and here we are overriding it to display only required clothes.
     */
}

