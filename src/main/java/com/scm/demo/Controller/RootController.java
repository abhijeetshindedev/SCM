package com.scm.demo.Controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.demo.Entities.User;
import com.scm.demo.config.OAuthHelper;
import com.scm.demo.service.UserService;

@ControllerAdvice
public class RootController {

    Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    UserService userService;
    
    // add logged in user details in every request in this controller
    @ModelAttribute
    public void addLoggedInUser(Model model, Principal principal){
        if (principal == null) {
            return;
        }
        // got principal as response from OAuthSuccessHandler
        Authentication authentication = (Authentication) principal;

        // Pass here to get email of logged in user
        String email = OAuthHelper.getEmailFromPrincipal(principal);
        log.info("user name in RootController : " + email);
        
        // fetch data from database
        User user = userService.getUserByEmail(email);
        // log.info("user : " + user);
        model.addAttribute("loggedInUser",user);
    }


}
