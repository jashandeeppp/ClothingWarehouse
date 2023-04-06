package com.cpan282.clotheswarehouse.controller;

import java.util.EnumSet;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan282.clotheswarehouse.model.Cloth.Brand;
import com.cpan282.clotheswarehouse.model.dto.ClothSearchByYearDto;
import com.cpan282.clotheswarehouse.repository.ClothRepository;
import com.cpan282.clotheswarehouse.repository.ClothRepositoryPaginated;


@Controller
@RequestMapping("/clothlist")
public class ClothListController {
    
    private ClothRepository clothRepository;        // clothrepository used for CRUD commands such as create, update, delete .

    private ClothRepositoryPaginated clothRepositoryPaginated;  // clothRepositoryPaginated for pagination.

    private static final int PAGE_SIZE = 5;

    public ClothListController(ClothRepository clothRepository, ClothRepositoryPaginated clothRepositoryPaginated){        // implemted the ClothRepostory interface.
        this.clothRepository = clothRepository;
        this.clothRepositoryPaginated = clothRepositoryPaginated;
    }

    @GetMapping
    public String showClothes(Model model){
        return "clothlist";
    }

    @ModelAttribute
    public void clothes(Model model){
        // model.addAttribute("clothes",clothRepository.findAll());
        var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(0,PAGE_SIZE));   // We provided the first page number and page size(means total number of items need to display on a single page.) and we assign all that data to clothPage and due to PageRequest it automatically divide the items into pages and with the findAll we are getting all the data from fighterRepositoryPaginated.
        model.addAttribute("clothes", clothPage.getContent());                  // retrieves the clothes from clothRepositoryPaginated by using clothPage which we have used abovea and have all the info
        model.addAttribute("currentPage", clothPage.getNumber());               // retrives the current page.
        model.addAttribute("totalPages", clothPage.getTotalPages());            // retrieves the total number of page.
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

    @GetMapping("/switchPage")
    public String switchPage(Model model, 
        @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch){
            var page = pageToSwitch.orElse(0);
            var totalPages = (int) model.getAttribute("totalPages");
            if (page< 0 || page >= totalPages){
                return "clothlist";
            }
            var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0), 
            PAGE_SIZE));
            model.addAttribute("clothes", clothPage.getContent());
            model.addAttribute("currentPage", clothPage.getNumber());
            return "clothlist";
        }


}

