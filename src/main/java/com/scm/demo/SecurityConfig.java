package com.scm.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.demo.service.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

   
    //user create and login using in memory userdetailsservice
    // @Bean
    // public UserDetailsService userDetailsService() {

    //     //create users
    //     UserDetails user1 = User.
    //                         withDefaultPasswordEncoder().
    //                         username("admin").
    //                         password("admin123").
    //                         roles("ADMIN","USER").
    //                         build();
    //     UserDetails user2 = User.
    //                         withDefaultPasswordEncoder().
    //                         username("user").
    //                         password("user123").
    //                         roles("USER").
    //                         build();


    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailsService;

    // Configuration of authentication provider for spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // create user detail service object

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // create password encoder object
        
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // configure securityFilterChain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // will configure which URl is allow and which not
        httpSecurity.authorizeHttpRequests( authorize -> {
            // authorize.requestMatchers("/home").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // FormLogin with Default Configuration
        // if we want to change anything to FormLogin then we will change here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}   
