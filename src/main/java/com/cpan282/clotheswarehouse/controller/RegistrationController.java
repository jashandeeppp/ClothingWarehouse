package com.cpan282.clotheswarehouse.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan282.clotheswarehouse.model.form.RegistrationForm;
import com.cpan282.clotheswarehouse.repository.UserRepository;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    /*
     * Esentially we have UserRepository and PasswordEncoder
     * PasswordEncoder comes from SecurityConfig.java
     */
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    // here, we inject UserRepository and PasswordEncoder
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // provided mapping for the view
    @GetMapping
    public String showRegistrationForm(){
        return "authentication/register";
    }

    // implemented registeredUserAccount capabilities which implement RegistrationFrom and save user.
    @PostMapping
    public String registerUserAccount(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }

}
