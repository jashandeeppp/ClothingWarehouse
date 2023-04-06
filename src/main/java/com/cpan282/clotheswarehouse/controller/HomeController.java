package com.cpan282.clotheswarehouse.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cpan282.clotheswarehouse.model.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
    
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @ModelAttribute
    public void user(Model model, @AuthenticationPrincipal User user, Authentication authentication) {
        if (user != null && authentication.isAuthenticated()) {
            log.info("Logged in");
            model.addAttribute("loggedIn", true);
            model.addAttribute("user", user.getUsername());
        } else {
            log.info("not logged ");
            model.addAttribute("loggedOut", true);
        }
    }

}
