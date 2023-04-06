package com.cpan282.clotheswarehouse.controller;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan282.clotheswarehouse.model.User;
import com.cpan282.clotheswarehouse.repository.ClothRepository;
import com.cpan282.clotheswarehouse.repository.ClothRepositoryPaginated;
import lombok.extern.slf4j.Slf4j;

@Slf4j // for logger
@Controller
@RequestMapping("/management")
@PreAuthorize("hasRole('ROLE_ADMIN')")      // Preauthorize protection which is used to authorize that only admin can delete all clothes.
public class ClothManagementController {

    private ClothRepository clothRepository;        // clothrepository used for CRUD commands such as create, update, delete .

    private ClothRepositoryPaginated clothRepositoryPaginated;  // clothRepositoryPaginated for pagination.

    private static final int PAGE_SIZE = 5;

    public ClothManagementController(ClothRepository clothRepository, ClothRepositoryPaginated clothRepositoryPaginated){        // implemted the ClothRepostory interface.
        this.clothRepository = clothRepository;
        this.clothRepositoryPaginated = clothRepositoryPaginated;
    }

    @GetMapping
    public String showmanagement(){
        return "management";
    }

    @ModelAttribute
    public void user(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user.getUsername());
    }


    @ModelAttribute
    public void clothes(Model model){
        // model.addAttribute("clothes",clothRepository.findAll());
        var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(0,PAGE_SIZE));   // We provided the first page number and page size(means total number of items need to display on a single page.) and we assign all that data to clothPage and due to PageRequest it automatically divide the items into pages and with the findAll we are getting all the data from fighterRepositoryPaginated.
        model.addAttribute("clothes", clothPage.getContent());                  // retrieves the clothes from clothRepositoryPaginated by using clothPage which we have used abovea and have all the info
        model.addAttribute("currentPage", clothPage.getNumber());               // retrives the current page.
        model.addAttribute("totalPages", clothPage.getTotalPages());            // retrieves the total number of page.
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model, 
        @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch){
            var page = pageToSwitch.orElse(0);
            var totalPages = (int) model.getAttribute("totalPages");
            if (page< 0 || page >= totalPages){
                return "management";
            }
            var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0), 
            PAGE_SIZE));
            model.addAttribute("clothes", clothPage.getContent());
            model.addAttribute("currentPage", clothPage.getNumber());
            return "management";
        }

    @PostMapping("/deleteAllClothes")
    public String processClothesDeletion(@AuthenticationPrincipal User user){
            log.info("Delete all clothes for user: ", user.getAuthorities());
            clothRepository.deleteAll();
            return "redirect:/management";
    }

    @PostMapping("/deleteCloth/{id}")
public String deleteCloth(@PathVariable Long id) {
    
    log.info("Deleting cloth with id: {}", id);
    clothRepository.deleteById(id);
    return "redirect:/management";
}
}
