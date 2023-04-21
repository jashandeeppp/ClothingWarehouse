package com.cpan282.clotheswarehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.cpan282.clotheswarehouse.model.User;
import com.cpan282.clotheswarehouse.repository.UserRepository;

@Configuration
/*
 * Configuration Class for spring is like a holder of beans
 * We can use this class to define beans that we want to use in our application
 */
@EnableMethodSecurity
public class SecurityConfig {
    
    /*
     * We need PasswordEncoder bean using BCryptPasswordEncoder implementation(Applies bcrypt string hashing encryption).
     * We need encoder to apply password encryption in the database, so user password is never stored in decrypted form and gets encrypted when user logs in and gets comapred with encrypted password stored in the database.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
     * We implemented userDetailsService and UserRepository
     * So, essentially we try to find user by findByUsername using username
     * If user is not null then we return user and if it is nuull then we will give UsernameNotFoundException.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null){
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean   //Here in this method we implemented some security chain.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
         * We authorize HTTP request to H2 console and we permit all the request.
         */
        http
            .authorizeHttpRequests()
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers("/design","/clothlist","/management","/distribution")
            .hasRole("USER")
            .anyRequest().permitAll()

            // defined login page with /login
            .and()
            .formLogin()
            .loginPage("/")
            .defaultSuccessUrl("/home", true)
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied")     // for access denying

            // and when we logout we logout to /url
            .and()
            .logout()
            .logoutSuccessUrl("/")

            .and()
            .headers()
            .frameOptions();

            http.csrf().disable();
            return http.build();

        

    }

    
}
